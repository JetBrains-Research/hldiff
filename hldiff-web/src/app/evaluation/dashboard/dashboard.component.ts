import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UploadDialogComponent } from '../upload-dialog/upload-dialog.component';
import { DiffData } from '../../diff-data';
import { MatPaginator } from '@angular/material/paginator';
import { DiffDataSource } from '../diff-data-source';
import { HLDiffService } from '../../hldiff.service';
import { tap } from 'rxjs/operators';
import { FileUploadComponent } from '../file-upload/file-upload.component';
import { AuthenticationService } from '../../users/authentication.service';

@Component({
  selector: 'app-upload',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit, AfterViewInit {

  // private diffs$: Observable<DiffData>;

  diffDataSource: DiffDataSource;

  displayedColumns = ['id', 'source', 'size', 'open', 'reviews', 'delete'];

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  constructor(public dialog: MatDialog,
              private diffService: HLDiffService,
              private loginService: AuthenticationService) {
  }

  ngOnInit(): void {
    this.diffDataSource = new DiffDataSource(this.diffService);
    this.diffDataSource.loadDiffs();
  }

  ngAfterViewInit() {
    this.paginator.page
      .pipe(
        tap(() => this.loadDiffPage())
      ).subscribe();
  }

  openUploadDialog() {
    this.dialog.open(UploadDialogComponent, { width: '750px', data: 'Add Diff' });
  }

  openFileUploadDialog() {
    this.dialog.open(FileUploadComponent, { width: '500px', data: 'Upload Diff Files' });
  }

  openVisualization(element: DiffData) {
    window.open(`/diff/evaluation/${element.id}`);
  }

  deleteDiff(diff) {
    this.diffService.removeDiff(diff.id).subscribe();
  }

  private loadDiffPage() {
    this.diffDataSource.loadDiffs(this.paginator.pageIndex, this.paginator.pageSize);
  }

  getUserName(): string {
    return this.loginService.getUser().username;
  }
}
