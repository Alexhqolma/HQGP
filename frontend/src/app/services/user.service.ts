import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { UserAuthService } from './user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  API_PATH = "http://localhost:8080";
  requestHeader = new HttpHeaders(
    { "No-Auth":"True" }
  );

  constructor(
    private httpclient: HttpClient,
    private userAuthService: UserAuthService) { }

  public login(loginData: NgForm) {
    return this.httpclient.post(this.API_PATH + "/auth/login", loginData, { headers: this.requestHeader });
  }

  public signup(signupData: NgForm) {
    return this.httpclient.post(this.API_PATH + "/auth/signup", signupData, { headers: this.requestHeader });
  }

  public roleMatch(allowedRoles: any): boolean {
    let isMatch = false;
    const userRole: string | null = this.userAuthService.getRole();
    if (userRole != null && userRole) {
      for (let i = 0; i < allowedRoles.length; i++) {
        if (userRole === allowedRoles[i]) {
          isMatch = true;
          return isMatch;
        }
      }
      return isMatch = true;
    } else {
      return isMatch;
    }
  }
}
