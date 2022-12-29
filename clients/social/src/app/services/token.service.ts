import {Injectable} from '@angular/core';
import Token, {TOKEN_STORE_KEY} from "../shared/domain/Token";
import jwtDecode from "jwt-decode";
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
      return jwtDecode(token);
    } catch (Error) {
      return null;
    }
  }
}
