import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor() {
  }

  isUserLoggedIn(): boolean {
    return true;
  }

  getUserName(): string {
    return 'user_name';
  }
}
