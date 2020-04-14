import { AfterViewInit, Component, OnInit } from '@angular/core';
import { SourceCodeType } from '../../visualization/source-code/source-code-type';
import { Observable } from 'rxjs';
import { HLDiff } from '../../hldiff';
import { HLDiffService } from '../../hldiff.service';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { AuthenticationService } from '../../users/authentication.service';
import { switchMap } from 'rxjs/operators';
import { MatDialog } from '@angular/material/dialog';
import { ActionEvaluationDialogComponent, EvalDialogData } from '../action-evaluation-dialog/action-evaluation-dialog.component';
import { Change } from '../../change';
import { ChangeActionEvaluation, Evaluation } from '../evaluation';
import { EvaluationService } from '../evaluation.service';

@Component({
  selector: 'app-diff-evaluation',
  templateUrl: './diff-evaluation.component.html',
  styleUrls: ['./diff-evaluation.component.scss']
})
export class DiffEvaluationComponent implements OnInit, AfterViewInit {

  sourceCodeType = SourceCodeType;
  diff$: Observable<HLDiff>;
  diff: HLDiff;

  canSubmit = false;

  changeActionsEvaluations: Map<number, ChangeActionEvaluation> = new Map<number, ChangeActionEvaluation>();
  comment: string;

  constructor(private hldiffService: HLDiffService,
              private route: ActivatedRoute,
              private evaluationService: EvaluationService,
              private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.diff$ = this.route.paramMap.pipe(
      switchMap((params: ParamMap) => this.hldiffService.getDiffById(params.get('id')))
    );
    this.diff$.subscribe(value => {
      this.diff = value;
      setTimeout(() => {
        const changesList = document.getElementsByClassName('change');
        this.positionChanges(changesList);
      }, 0);
    });
  }

  ngAfterViewInit(): void {
    const changedCode = document.getElementsByClassName('changed-code');


    const line = document.getElementById('line');

    this.setChangedCodeListeners(changedCode, line);
  }

  setChangedCodeListeners(changedCode, line) {
    for (const elem of changedCode) {
      elem.addEventListener('mouseenter', () => {
        const changeId = elem.className.split(' ').find((v) => v.startsWith('code-change-'));

        const start = elem;
        const end = document.getElementById(changeId);
      });
    }
  }

  positionChanges(changes) {
    const diffContainer = document.querySelectorAll<HTMLElement>('.diff')[0];
    for (let i = 0; i < changes.length; i++) {
      const codeChange = document.querySelectorAll<HTMLElement>('.' + changes[i].id)[0];
      changes[i].style.top = Math.max(codeChange.offsetTop - diffContainer.offsetTop,
        i > 0 ? changes[i - 1].offsetTop + changes[i - 1].offsetHeight + 10 : 0) + 'px';
    }
  }

  submitEvaluation() {
    const evaluation = {
      diffId: this.diff.id,
      actions: Array.from(this.changeActionsEvaluations.values()),
      comment: this.comment,
    } as Evaluation;

    this.evaluationService.submitEvaluation(evaluation).subscribe(
      success => {
      }
    );
  }

  openActionEvaluationDialog(change: Change) {
    const changeEval = this.changeActionsEvaluations.get(change.id) || {} as ChangeActionEvaluation;
    console.log(changeEval);
    const dialogRef = this.dialog.open(ActionEvaluationDialogComponent, {
      width: '750px',
      data: { qualityValue: changeEval.qualityValue, comment: changeEval.comment } as EvalDialogData
    });
    dialogRef.afterClosed().subscribe((result: EvalDialogData) => {
      if (result && result.qualityValue !== null) {
        this.changeActionsEvaluations.set(change.id, {
          actionID: change.id,
          comment: result.comment,
          qualityValue: result.qualityValue
        } as ChangeActionEvaluation);
        console.log(this.changeActionsEvaluations);
        console.log(this.changeActionsEvaluations.size);
        console.log(this.diff.highLevelActions.length);
        this.canSubmit = this.changeActionsEvaluations.size === this.diff.highLevelActions.length;
      }
    });
  }
}
