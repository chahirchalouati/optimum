import {Injectable} from '@angular/core';
import Token, {TOKEN_STORE_KEY} from "../shared/domain/Token";
import jwtDecode, {JwtPayload} from "jwt-decode";
import {StorageService} from "./storage.service";

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor(private storageService: StorageService) {
  }

  getUserInfo(): any {
    const token = this.storageService.getItem(TOKEN_STORE_KEY) as Token;
    return this.getDecodedAccessToken(token.access_token)
  }

  getDecodedAccessToken(token: string): any {
    try {
      return jwtDecode<JwtPayload>(token);
    } catch (Error) {
      return null;
    }
  }

  clear(): void {
    this.storageService.removeItem(TOKEN_STORE_KEY)
  }

  tokenExists(): boolean {
    return true
  }

  isTokenExpired() {
    return false;
  }
}
