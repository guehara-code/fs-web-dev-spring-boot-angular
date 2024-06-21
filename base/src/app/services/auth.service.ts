import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { LoginRequest } from '../model/login.model';
import { LoginResponse } from '../model/login.model'
import { environment } from 'src/environments/environment';
import { JwtHelperService } from '@auth0/angular-jwt';
import { LoggedUser } from '../model/logged-user.model';

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  jwtHelperService = new JwtHelperService();
  user = new BehaviorSubject<LoggedUser | null>(null);

  constructor(private http: HttpClient) {

  }

  public login(user: LoginRequest): Observable<LoginResponse> {
    const formData = new FormData 
    formData.append('username', user.username);
    formData.append('password', user.password);
    return this.http.post<LoginResponse>(environment.backendHost + "/login", formData);
  }

  saveToken(jwtTokens: LoginResponse) {
    const decodeAccessToken = this.jwtHelperService.decodeToken(jwtTokens.accessToken);
    const loggedUser = new LoggedUser(decodeAccessToken.sub, decodeAccessToken.roles, 
      jwtTokens.accessToken, this.getExpirationDate(decodeAccessToken.exp));
    this.user.next(loggedUser);
  }

  getExpirationDate(exp: number) {
    const date = new Date(0);
    date.setUTCSeconds(exp);
    return date;
  }

}
