import { Injectable } from '@angular/core';
import { HLDiff } from './hldiff';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../environments/environment';
import { map, tap } from 'rxjs/operators';
import { LoggerService } from './logger.service';
import { DiffData } from './diff-data';

@Injectable({
  providedIn: 'root'
})
export class HLDiffService {

  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    'Access-Control-Allow-Origin': '*',
  };

  constructor(private http: HttpClient,
              private logger: LoggerService) {
  }

  getDiffById(id: string): Observable<HLDiff> {
    return this.http.get<DiffData>(environment.apiUrl + '/diff/' + id, this.httpOptions).pipe(
      map(data => {
        const diff = JSON.parse(data.data) as HLDiff;
        diff.id = data.id;
        return diff;
      }),
      tap(diff => this.log(`Fetched diff with id=${diff.id}`))
    );
  }

  uploadDiff(diff: DiffData): Observable<DiffData> {
    return this.http.post<DiffData>(environment.apiUrl + '/diff', diff, this.httpOptions).pipe(
      tap(uploadedDiff => this.log(`Uploaded diff with id=${uploadedDiff.id}`))
    );
  }

  findDiffs(pageIndex: number, pageSize: number): Observable<DiffData[]> {
    return this.http.get<DiffData[]>(environment.apiUrl + '/diff').pipe(
      tap(diffs => this.log(`Fetched ${diffs.length} diffs`))
    );
  }

  private log(message: string) {
    this.logger.log(`HLDiffService: ${message}`);
  }

  private handleError<T>(operation: string, result?: T) {
    return (error: any): Observable<T> => {
      console.log(error);
      this.log(`operation ${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}
