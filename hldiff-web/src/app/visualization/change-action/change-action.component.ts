import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { Change } from '../../change';
import { clearLine, drawLine, setAlpha } from '../../draw-utils';

@Component({
  selector: 'app-change-action',
  templateUrl: './change-action.component.html',
  styleUrls: ['./change-action.component.scss']
})
export class ChangeActionComponent implements OnInit {

  @Input()
  change: Change;

  @Input()
  checked = false;

  @ViewChild('rootChange')
  private element: ElementRef;

  constructor(element: ElementRef) {
    this.element = element;
  }

  ngOnInit(): void {
  }

  enter(): void {
    const line1 = document.getElementById('line');
    const line2 = document.getElementById('line2');
    const codeChanges = document.querySelectorAll<HTMLElement>('.code-change-' + this.change.id);

    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < codeChanges.length; i++) {
      const change = codeChanges[i];
      change.style.backgroundColor = setAlpha(document.defaultView.getComputedStyle(change, null)['background-color'], 1);
    }

    switch (this.change.type) {
      case 'add':
        drawLine(codeChanges[0], this.element.nativeElement, line1, 'var(--add-color)');
        break;

      case 'move':
        drawLine(codeChanges[0], this.element.nativeElement, line1, 'var(--move-color)');
        drawLine(codeChanges[1], this.element.nativeElement, line2, 'var(--move-color)');
        break;

      case 'delete':
        drawLine(codeChanges[0], this.element.nativeElement, line1, 'var(--delete-color)');
        break;

      case 'update':
        drawLine(codeChanges[0], this.element.nativeElement, line1, 'var(--update-color)');
        drawLine(codeChanges[1], this.element.nativeElement, line2, 'var(--update-color)');
        break;
    }
  }

  leave(): void {
    const line1 = document.getElementById('line');
    const line2 = document.getElementById('line2');

    clearLine(line1);
    clearLine(line2);

    const codeChanges = document.querySelectorAll<HTMLElement>('.code-change-' + this.change.id);

    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < codeChanges.length; i++) {
      const change = codeChanges[i];
      change.style.backgroundColor = setAlpha(document.defaultView.getComputedStyle(change, null)['background-color'], 0.3);
    }
  }
}
