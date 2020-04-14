import { Component, Input, OnInit } from '@angular/core';
import { Change } from '../../change';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { EvaluationService } from '../evaluation.service';
import { MatDialogRef } from '@angular/material/dialog';
import { LoginDialogComponent } from '../../users/login-dialog/login-dialog.component';

@Component({
  selector: 'app-action-evaluation-dialog',
  templateUrl: './action-evaluation-dialog.component.html',
  styleUrls: ['./action-evaluation-dialog.component.scss']
})
export class ActionEvaluationDialogComponent implements OnInit {

  @Input()
  change: Change;

  error: string;

  form: FormGroup = new FormGroup({
    comment: new FormControl(''),
    radio: new FormControl('', [Validators.required]),
  });

  selectedModel: string;

  constructor(private evaluationService: EvaluationService,
              private dialogRef: MatDialogRef<LoginDialogComponent>) {
  }

  ngOnInit(): void {
  }

  submit() {
    this.dialogRef.close();
  }
}
