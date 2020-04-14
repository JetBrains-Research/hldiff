import { Component, Inject, Input, OnInit } from '@angular/core';
import { Change } from '../../change';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { EvaluationService } from '../evaluation.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { LoginDialogComponent } from '../../users/login-dialog/login-dialog.component';

export interface EvalDialogData {
  qualityValue: string;
  comment: string;
}

@Component({
  selector: 'app-action-evaluation-dialog',
  templateUrl: './action-evaluation-dialog.component.html',
  styleUrls: ['./action-evaluation-dialog.component.scss']
})
export class ActionEvaluationDialogComponent implements OnInit {

  @Input()
  change: Change;

  constructor(@Inject(MAT_DIALOG_DATA) public data: EvalDialogData) {
  }

  ngOnInit(): void {
  }
}
