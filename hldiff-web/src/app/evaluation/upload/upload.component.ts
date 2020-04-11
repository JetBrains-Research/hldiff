import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UploadDialogComponent } from '../upload-dialog/upload-dialog.component';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.scss']
})
export class UploadComponent implements OnInit {


  constructor(public dialog: MatDialog) {
  }

  ngOnInit(): void {
  }


  openUploadDialog() {
    this.dialog.open(UploadDialogComponent, { width: '750px', data: 'Add Diff' });
  }
}
