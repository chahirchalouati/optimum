import {Injectable} from '@angular/core';
import {JwtHelperService} from '@auth0/angular-jwt';
import Token from "../shared/domain/Token";
import jwtDecode, {JwtPayload} from "jwt-decode";

const TOKEN_KEY = 'access_token';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  private jwtHelper = new JwtHelperService();

  constructor() {
  }

  getToken(): Token | null {
    const token = localStorage.getItem(TOKEN_KEY);
    return !!token ? JSON.parse(token) as Token : null;
  }

  saveToken(token: Token): boolean {
    localStorage.setItem(TOKEN_KEY, JSON.stringify(token));
    return !!token;
  }

  removeToken(): void {
    localStorage.removeItem(TOKEN_KEY);
  }

  isTokenExpired(): boolean {
    const token = this.getToken();
    if (!token) {
      return true;
    }
    return this.jwtHelper.isTokenExpired(token.access_token);
  }

  isAuthenticated(): boolean {
    return !this.isTokenExpired();
  }

  getUserInfo(): any {
    const token = this.getToken();
    return !!token ? jwtDecode<JwtPayload>(token.access_token) : null;
  }
}
