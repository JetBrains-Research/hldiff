import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UploadDialogComponent } from '../upload-dialog/upload-dialog.component';
import { Observable } from 'rxjs';
import { DiffData } from '../../diff-data';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-upload',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  private diffs$: Observable<DiffData>;

  diffDatasource = new MatTableDataSource([
    { id: '123', data: '{"Hello": "World"}', source: 'github' } as DiffData,
    { id: '123', data: '{"Hello": "World"}', source: 'github' } as DiffData,
    { id: '123', data: '{"Hello": "World"}', source: 'github' } as DiffData,
    { id: '123', data: '{"Hello": "World"}', source: 'github' } as DiffData,
    { id: '123', data: '{"Hello": "World"}', source: 'github' } as DiffData,
    { id: '123', data: '{"Hello": "World"}', source: 'github' } as DiffData,
    { id: '123', data: '{"Hello": "World"}', source: 'github' } as DiffData,
    { id: '123', data: '{"Hello": "World"}', source: 'github' } as DiffData,
    { id: '123', data: '{"Hello": "World"}', source: 'github' } as DiffData,
    { id: '123', data: '{"Hello": "World"}', source: 'github' } as DiffData,
    { id: '123', data: '{"Hello": "World"}', source: 'github' } as DiffData,
    { id: '123', data: '{"Hello": "World"}', source: 'github' } as DiffData,
    { id: '123', data: '{"Hello": "World"}', source: 'github' } as DiffData,
    { id: '123', data: '{"Hello": "World"}', source: 'github' } as DiffData,
    { id: '123', data: '{"Hello": "World"}', source: 'github' } as DiffData,
    { id: '123', data: '{"Hello": "World"}', source: 'github' } as DiffData,
    { id: '123', data: '{"Hello": "World"}', source: 'github' } as DiffData,
    { id: '123', data: '{"Hello": "World"}', source: 'github' } as DiffData,
    { id: '123', data: '{"Hello": "World"}', source: 'github' } as DiffData,
    { id: '123', data: '{"Hello": "World"}', source: 'github' } as DiffData,
    { id: '123', data: '{"Hello": "World"}', source: 'github' } as DiffData,
    { id: '123', data: '{"Hello": "World"}', source: 'github' } as DiffData,
    { id: '123', data: '{"Hello": "World"}', source: 'github' } as DiffData,
    { id: '123', data: '{"Hello": "World"}', source: 'github' } as DiffData,
    { id: '123', data: '{"Hello": "World"}', source: 'github' } as DiffData,
    { id: '123', data: '{"Hello": "World"}', source: 'github' } as DiffData
  ]);

  displayedColumns = ['id', 'source', 'open'];

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  constructor(public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.diffDatasource.paginator = this.paginator;
  }


  openUploadDialog() {
    this.dialog.open(UploadDialogComponent, { width: '750px', data: 'Add Diff' });
  }

  openVisualization(element: DiffData) {
    window.open(`/diff/${element.id}`);
  }
}
