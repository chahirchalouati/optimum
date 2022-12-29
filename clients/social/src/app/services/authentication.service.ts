import {Injectable} from '@angular/core';
import {map, Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import Token, {CLIENT_ID, CLIENT_SECRET, CODE, GRANT_TYPE, REDIRECT_URI, TOKEN_STORE_KEY} from "../shared/domain/Token";
import {StorageService} from "./storage.service";
import {environment} from "../../environment/environment";
import GenericCrud from "../shared/domain/GenericCrud";
import User from '../shared/domain/User';
import {Pageable} from "../shared/domain/Pageable";

export interface UserInfo {
  firstName: string;
  lastName: string;
  username: string;
  email: string;
  roles: string[];
}

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService implements GenericCrud<User> {

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

  get(pageRequest: Pageable.PageRequest): Observable<Pageable.Page<User>> {
    return this.httpClient.get<Pageable.Page<User>>(environment.api.user.USER_GET_PAGE, {params: {...pageRequest}});
  }

  getId(id: string | number): Observable<Pageable.Page<User>> {
    return this.httpClient.get<Pageable.Page<User>>(environment.api.user.USER_GET_BY_ID, {params: {id}});
  }

  post(payload: User): Observable<User> {
    return this.httpClient.get<User>(environment.api.user.USER_POST);
  }

  put(payload: User, id: string | number): Observable<User> {
    return this.httpClient.put<User>(environment.api.user.USER_PUT, payload, {params: {id}});
  }

  delete(id: string | number): Observable<User> {
    return this.httpClient.delete<User>(environment.api.user.USER_DELETE, {params: {id}});
  }

}
