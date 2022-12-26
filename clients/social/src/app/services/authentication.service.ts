import {Injectable} from '@angular/core';
import {map, Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import Token, {CLIENT_ID, CLIENT_SECRET, CODE, GRANT_TYPE, REDIRECT_URI, TOKEN_STORE_KEY} from "../shared/domain/Token";
import {StorageService} from "./storage.service";
import {environment} from "../../environment/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private httpClient: HttpClient, private storageService: StorageService) {
  }

  getAuthorizationCode() {
    window.location.href = environment.oauthAuthorizeUrl +
      '?' +
      'client_id=' + environment.oauthClientId +
      '&' +
      'redirect_uri=' + environment.oauthCallbackUrl +
      '&' +
      'scope=' + environment.scope +
      '&' +
      'response_type=' + environment.responseType
  }

  getAccessToken(code: string): Observable<{ isAuthenticated: boolean, token: Token }> {
    const options = {headers: {'Content-Type': 'application/x-www-form-urlencoded'}};
    const payload = new HttpParams()
      .append(GRANT_TYPE, environment.grantType)
      .append(CODE, code)
      .append(REDIRECT_URI, environment.oauthCallbackUrl)
      .append(CLIENT_ID, environment.oauthClientId)
      .append(CLIENT_SECRET, environment.oauthClientSecret);

    return this.httpClient.post<Token>(environment.oauthTokenUrl, payload, options).pipe(
      map((token) => {
        const isTokenPresent = !!token.access_token;
        if (isTokenPresent) {
          this.storageService.setItem(TOKEN_STORE_KEY, token);
        }
        return {isAuthenticated: isTokenPresent, token};
      }));
  }

  isAuthenticated() {
    return !!this.storageService.getItem(TOKEN_STORE_KEY);
  }
}
