import { Component, Input, OnInit } from '@angular/core';
import { CodeFragment } from '../../code-fragment';

@Component({
  selector: 'app-code-fragment',
  templateUrl: './code-fragment.component.html',
  styleUrls: ['./code-fragment.component.scss']
})
export class CodeFragmentComponent implements OnInit {

  @Input()
  codeFragment: CodeFragment;

  constructor() {
  }

  ngOnInit(): void {
  }

}
