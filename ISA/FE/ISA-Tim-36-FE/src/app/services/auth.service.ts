import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  loggedIn = false;

  constructor() { }

  isMedicalCenterAdmin() {
    return Math.random() > 0.5;
  }

  isAdmin() {
    return false; // mocked data
  }

  isLoggedIn() {
    return this.loggedIn;
  }

  login() {
    this.loggedIn = true;
  }
}
