import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor() {
  }

  authenticate(username: string, password: string): boolean {
    if (username === 'karvozavr' && password === '123') {
      sessionStorage.setItem('username', username);
      return true;
    } else {
      return false;
    }
  }

  isUserLoggedIn(): boolean {
    return sessionStorage.getItem('username') !== null;
  }

  getUserName(): string {
    return sessionStorage.getItem('username');
  }

  logout() {
    sessionStorage.removeItem('username');
  }
}
