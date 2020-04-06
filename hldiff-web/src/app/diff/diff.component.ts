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
    console.log(changedCode.length);

    const line = document.getElementById('line');
    const line2 = document.getElementById('line2');

    // this.positionChanges(changesList, changedCode);
    // this.setChangedCodeListeners(changedCode, line);
    // this.setChangesListListeners(changesList, line, line2);
  }

  /* drawLine(start, end, line, color = 'rgb(0, 0, 0)') {
     let x1 = start.getBoundingClientRect().left;
     let y1 = start.offsetTop + start.getBoundingClientRect().height / 2;
     let x2 = end.getBoundingClientRect().left;
     let y2 = end.getBoundingClientRect().top + window.scrollY + end.clientHeight / 2;

     if (x1 < x2) { // code is to the left
       x1 += start.getBoundingClientRect().width;
     } else {
       x2 += end.clientWidth;
     }

     document.documentElement.style.setProperty('--line-color', this.setAlpha(color, 1));
     line.setAttribute('x1', x1);
     line.setAttribute('y1', y1);
     line.setAttribute('x2', x2);
     line.setAttribute('y2', y2);
   }

   setChangedCodeListeners(changedCode, line) {
     for (let elem of changedCode) {
       elem.addEventListener('mouseenter', () => {
         let changeId = elem.className.split(' ').find((v) => v.startsWith('code-change-'));

         const start = elem;
         const end = document.getElementById(changeId);

         console.log(start);
         this.drawLine(start, end, line);
       });
       console.log(elem.offsetTop)
     }
   }

   setAlpha(rgba, alpha) {
     console.log(rgba);
     if (rgba.startsWith('rgba')) {
       return rgba.replace(/ ([^ ]*)\)/, alpha);
     } else {
       return rgba.replace('rgb', 'rgba').replace(/(\))/, ', ' + alpha + ')');
     }
   }

   setChangesListListeners(changesList, line, line2) {
     for (let elem of changesList) {
       const codeChanges = document.querySelectorAll<HTMLElement>('.' + elem.id);

       elem.addEventListener('mouseenter', () => {
         for (let i = 0; i < codeChanges.length; i++) {
           const change = codeChanges[i];
           console.log("Enter", change.style.backgroundColor);
           change.style.backgroundColor = this.setAlpha(document.defaultView.getComputedStyle(change, null)['background-color'], 1);
         }

         if (elem.className.includes('change-addition')) {
           this.drawLine(codeChanges[0], elem, line, 'var(--add-color)');
         } else if (elem.className.includes('change-move')) {
           this.drawLine(codeChanges[0], elem, line, 'var(--move-color)');
           this.drawLine(codeChanges[1], elem, line2, 'var(--move-color)');
         } else if (elem.className.includes('change-delete')) {
           this.drawLine(codeChanges[0], elem, line, 'var(--delete-color)');
         } else if (elem.className.includes('change-update')) {
           this.drawLine(codeChanges[0], elem, line, 'var(--update-color)');
           this.drawLine(codeChanges[1], elem, line2, 'var(--update-color)');
         }
       });

       elem.addEventListener('mouseout', () => {
         for (let change of codeChanges) {
           line.setAttribute('x1', 0);
           line.setAttribute('y1', 0);
           line.setAttribute('x2', 0);
           line.setAttribute('y2', 0);
           line2.setAttribute('x1', 0);
           line2.setAttribute('y1', 0);
           line2.setAttribute('x2', 0);
           line2.setAttribute('y2', 0);
           console.log("Exit", change.style.backgroundColor);
           change.style.backgroundColor = this.setAlpha(document.defaultView.getComputedStyle(change, null)['background-color'], 0.3);
         }
       });
     }
   }

   positionChanges(changes, changedCode) {
     const diffContainer = document.getElementsByClassName('diff')[0];
     const codeChange = document.getElementsByClassName(changes[0].id)[0];
     changes[0].style.top = (codeChange.offsetTop - diffContainer.offsetTop) + 'px';
     for (let i = 1; i < changes.length; i++) {
       const codeChange = document.getElementsByClassName(changes[i].id)[0];
       console.log(codeChange.offsetTop + " positioning");
       changes[i].style.top = Math.max(codeChange.offsetTop - diffContainer.offsetTop, changes[i - 1].offsetTop + changes[i - 1].offsetHeight + 10) + 'px';
     }
   }*/

}