import { AfterViewInit, Component, OnInit } from '@angular/core';
import { SourceCodeType } from '../../visualization/source-code/source-code-type';
import { Observable } from 'rxjs';
import { HLDiff } from '../../hldiff';
import { HLDiffService } from '../../hldiff.service';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { switchMap, tap } from 'rxjs/operators';
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



  changeActionsEvaluations: Map<number, ChangeActionEvaluation> = new Map<number, ChangeActionEvaluation>();
  comment: string;
  error: string;

  constructor(private hldiffService: HLDiffService,
              private route: ActivatedRoute,
              private evaluationService: EvaluationService,
              private dialog: MatDialog,
              private router: Router) {
  }

  ngOnInit(): void {
    this.diff$ = this.route.paramMap.pipe(
      switchMap((params: ParamMap) => this.hldiffService.getDiffById(params.get('id')))
    );
    this.diff$.pipe(
      tap(value => this.evaluationService.getEvaluation(value.id).subscribe(
        evaluation => {
          this.changeActionsEvaluations = new Map(
            evaluation.actionsEvaluation.map(x => [x.actionId, x] as [number, ChangeActionEvaluation])
          );
          this.comment = evaluation.comment;
        },
        _ => {
          this.changeActionsEvaluations = new Map();
        }
      ))
    ).subscribe(value => {
      this.diff = value;
      setTimeout(() => {
        const changesList = document.getElementsByClassName('change');
        this.positionChanges(changesList);
      }, 0);
    });
  }

  ngAfterViewInit(): void {
  }

  canSubmit(): boolean {
    return this.changeActionsEvaluations.size === this.diff.highLevelActions.length;
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
    console.log('Heeey!!!!');

    this.error = null;

    const evaluation = {
      diffId: this.diff.id,
      actionsEvaluation: Array.from(this.changeActionsEvaluations.values()),
      comment: this.comment,
    } as Evaluation;

    this.evaluationService.submitEvaluation(evaluation).subscribe(
      _ => {
        console.log('Success');
        this.router.navigate(['/diff', { id: this.diff.id }]);
      },
      err => {
        console.log('Failure');
        this.error = `Upload failed with error: ${err}`;
      }
    );

    console.log('Submitted');
  }

  openActionEvaluationDialog(change: Change) {
    const changeEval = this.changeActionsEvaluations.get(change.id) || {} as ChangeActionEvaluation;
    const dialogRef = this.dialog.open(ActionEvaluationDialogComponent, {
      width: '750px',
      data: { qualityValue: changeEval.scoreLabel, comment: changeEval.comment } as EvalDialogData
    });
    dialogRef.afterClosed().subscribe((result: EvalDialogData) => {
      if (result && result.qualityValue !== null) {
        this.changeActionsEvaluations.set(change.id, {
          actionId: change.id,
          comment: result.comment,
          scoreLabel: result.qualityValue
        } as ChangeActionEvaluation);
      }
    });
  }
}
