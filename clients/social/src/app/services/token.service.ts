import {Injectable} from '@angular/core';
import Token, {TOKEN_STORE_KEY} from "../shared/domain/Token";
import jwtDecode, {JwtPayload} from "jwt-decode";
import {StorageService} from "./storage.service";

export interface ITokenService {
  save(token: Token): boolean;

  get(): Token;

  isAuthenticated(): boolean;

  isExpired(token: number): boolean;

  getUserInfo(): any;

  getDecodedAccessToken(token: string): any;

  clear(): void;
}

@Injectable({
  providedIn: 'root'
})
export class TokenService implements ITokenService {
  private store: any = null;

  constructor(private storageService: StorageService) {
    this.store = this.storageService.with("localStorage");
  }

  getUserInfo(): any {
    return this.getDecodedAccessToken(this.get().access_token)
  }

  getDecodedAccessToken(token: string): JwtPayload | null {
    try {
      return jwtDecode<JwtPayload>(token);
    } catch (Error) {
      return null;
    }
  }

  get(): Token {
    return this.store.getItem(TOKEN_STORE_KEY) as Token;
  }

  isAuthenticated(): boolean {
    const expiresIn = this.get()?.access_token;
    return expiresIn ? this.isTokenExpired(expiresIn) : false;
  }

  save(token: Token): boolean {
    this.store.removeItem(TOKEN_STORE_KEY);
    return !!this.store.setItem(TOKEN_STORE_KEY, token);
  }

  clear(): void {
    this.store.removeItem(TOKEN_STORE_KEY)
  }

  isExpired(token: number): boolean {
    return false;
  }

  tokenExists(): boolean {
    return !!this.get();
  }

  private isTokenExpired(token: string) {
    const expiry = (JSON.parse(atob(token.split('.')[1]))).exp;
    return expiry * 1000 > Date.now();
  }
}
