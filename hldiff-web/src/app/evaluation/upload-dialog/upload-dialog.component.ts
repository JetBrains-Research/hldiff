import { Component, OnInit } from '@angular/core';
import { DiffData } from '../../diff-data';
import { HLDiffService } from '../../hldiff.service';
import { MatDialogRef } from '@angular/material/dialog';
import { FormControl, Validators } from '@angular/forms';
import { jsonValidator } from '../json-validator';

@Component({
  selector: 'app-upload-dialog',
  templateUrl: './upload-dialog.component.html',
  styleUrls: ['./upload-dialog.component.scss']
})
export class UploadDialogComponent implements OnInit {
  diffSource: string;
  diffData: string;

  isLoading = false;
  errorMessage: string;
  diffSourceControl: FormControl;
  diffDataControl: FormControl;

  constructor(private diffService: HLDiffService,
              public dialogRef: MatDialogRef<UploadDialogComponent>) {
  }

  ngOnInit(): void {
    this.diffSourceControl = new FormControl('', [Validators.required]);
    this.diffDataControl = new FormControl('', [Validators.required, jsonValidator()]);
  }

  uploadDiff() {
    this.errorMessage = '';
    this.isLoading = true;
    const diffData = { source: this.diffSource, data: this.diffData, reviews: [] } as DiffData;
    const uploadedDiff$ = this.diffService.uploadDiff(diffData);

    uploadedDiff$.subscribe(
      _ => {
        this.isLoading = false;
        this.dialogRef.close();
      },
      err => {
        this.isLoading = false;
        this.errorMessage = err.message;
      },
    );
  }

  isFormValid(): boolean {
    return this.diffDataControl?.valid && this.diffSourceControl?.valid;
  }
}
