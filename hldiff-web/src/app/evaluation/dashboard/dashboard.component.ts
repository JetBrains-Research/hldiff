import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UploadDialogComponent } from '../upload-dialog/upload-dialog.component';

@Component({
  selector: 'app-upload',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {


  constructor(public dialog: MatDialog) {
  }

  ngOnInit(): void {
  }


  openUploadDialog() {
    this.dialog.open(UploadDialogComponent, { width: '750px', data: 'Add Diff' });
  }
}
