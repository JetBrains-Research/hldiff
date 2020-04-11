import { CollectionViewer, DataSource } from '@angular/cdk/collections';
import { DiffData } from '../diff-data';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { HLDiffService } from '../hldiff.service';
import { catchError, finalize } from 'rxjs/operators';

export class DiffDataSource implements DataSource<DiffData> {

  private diffsSubject = new BehaviorSubject<DiffData[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);

  public loading$ = this.loadingSubject.asObservable();

  constructor(private diffService: HLDiffService) {
  }

  connect(collectionViewer: CollectionViewer): Observable<DiffData[]> {
    return this.diffsSubject.asObservable();
  }

  disconnect(collectionViewer: CollectionViewer): void {
    this.diffsSubject.complete();
    this.loadingSubject.complete();
  }

  loadDiffs(pageIndex = 0, pageSize = 10) {
    this.loadingSubject.next(true);

    this.diffService.findDiffs(pageIndex, pageSize).pipe(
      catchError(() => of([])),
      finalize(() => this.loadingSubject.next(false))
    ).subscribe(lessons => this.diffsSubject.next(lessons));
  }
}
