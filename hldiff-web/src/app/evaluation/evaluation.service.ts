import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Evaluation } from './evaluation';
import { AuthenticationService } from '../users/authentication.service';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EvaluationService {

  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  constructor(private http: HttpClient,
              private authenticationService: AuthenticationService) {
  }

  getEvaluation(diffId: string): Observable<Evaluation> {
    return this.http.get<Evaluation>(
      `${environment.apiUrl}/evaluation?diffId=${diffId}&author=${this.authenticationService.getUser().username}`,
      this.httpOptions);
  }

  submitEvaluation(evaluation: Evaluation): Observable<any> {
    evaluation.author = this.authenticationService.getUser().username;
    return this.http.post<Evaluation>(`${environment.apiUrl}/evaluation`, evaluation,  this.httpOptions);
  }
}
