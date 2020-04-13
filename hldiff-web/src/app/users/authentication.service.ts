import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDto } from './user-dto';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private USER_KEY = 'currentUser';

  constructor(private http: HttpClient) {
  }

  authenticate(username: string, password: string): Observable<User> {
    return this.http.post<UserDto>(`${environment.apiUrl}/user/authenticate`, { username, password } as UserDto).pipe(
      tap(user => {
        if (user) {
          user.authdata = window.btoa(user.username + ':' + user.password);
          sessionStorage.setItem(this.USER_KEY, JSON.stringify(user));
        }
      }),
      map(user => user as UserDto)
    );
  }

  register(username: string, password: string): Observable<User> {
    return this.http.post<UserDto>(`${environment.apiUrl}/user/register`, { username, password } as UserDto);
  }

  isUserLoggedIn(): boolean {
    return sessionStorage.getItem(this.USER_KEY) !== null;
  }

  getUser(): User {
    return JSON.parse(sessionStorage.getItem(this.USER_KEY)) as UserDto;
  }

  logout() {
    sessionStorage.removeItem('username');
  }
}
