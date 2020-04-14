import { AfterViewInit, Component, OnInit } from '@angular/core';
import { SourceCodeType } from '../../visualization/source-code/source-code-type';
import { Observable } from 'rxjs';
import { HLDiff } from '../../hldiff';
import { HLDiffService } from '../../hldiff.service';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { AuthenticationService } from '../../users/authentication.service';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-diff-evaluation',
  templateUrl: './diff-evaluation.component.html',
  styleUrls: ['./diff-evaluation.component.scss']
})
export class DiffEvaluationComponent implements OnInit, AfterViewInit {

  sourceCodeType = SourceCodeType;
  diff$: Observable<HLDiff>;
  diff: HLDiff;

  constructor(private hldiffService: HLDiffService,
              private route: ActivatedRoute,
              private authenticationService: AuthenticationService) {
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
        console.log('Positioned');
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

        // this.drawLine(start, end, line);
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

  }

  openActionEvaluationDialog() {

  }
}
