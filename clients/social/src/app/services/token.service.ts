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
    const expiresIn = this.get()?.expires_in;
    return expiresIn ? this.isExpired(expiresIn) : false;
  }

  isExpired(expires_in: number): boolean {
    return (expires_in ?? 0) < Date.now() / 1000;
  }

  save(token: Token): boolean {
    return !!this.store.setItem(TOKEN_STORE_KEY, token);
  }

  hasValidRefreshToken() {
    return this.getDecodedAccessToken(this.get()?.refresh_token)?.exp ?? 0 < Date.now() / 1000;
  }

  clear(): void {
    this.store.removeItem(TOKEN_STORE_KEY)
  }
}
