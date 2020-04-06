import { AfterViewInit, Component, OnInit } from '@angular/core';
import { HLDiffService } from '../hldiff.service';
import { HLDiff } from '../hldiff';
import { SourceCodeType } from '../source-code/source-code-type';

@Component({
  selector: 'app-diff',
  templateUrl: './diff.component.html',
  styleUrls: ['./diff.component.scss']
})
export class DiffComponent implements OnInit, AfterViewInit {

  sourceCodeType = SourceCodeType;
  diff: HLDiff;

  private hldiffService: HLDiffService;

  constructor(hldiffService: HLDiffService) {
    this.hldiffService = hldiffService;
  }

  ngOnInit(): void {
    this.hldiffService.getDiffById('42').subscribe(hldiff => {
      this.diff = hldiff;
    });
  }

  ngAfterViewInit(): void {
    const changedCode = document.getElementsByClassName('changed-code');
    const changesList = document.getElementsByClassName('change');

    const line = document.getElementById('line');
    const line2 = document.getElementById('line2');

    this.positionChanges(changesList);
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

}
