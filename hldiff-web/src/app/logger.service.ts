import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoggerService {

  constructor() {
  }

  log(message: string, obj: any = '') {
    console.log(obj + message);
  }

}
