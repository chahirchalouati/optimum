import {Injectable} from '@angular/core';
import {EMPTY, map, Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import Token, {CLIENT_ID, CLIENT_SECRET, CODE, GRANT_TYPE, REDIRECT_URI} from "../shared/domain/Token";
import {environment} from "../../environment/environment";
import GenericCrud from "../shared/domain/GenericCrud";
import User from '../shared/domain/User';
import {Pageable} from "../shared/domain/Pageable";
import {TokenService} from "./token.service";

export interface IAuthenticationService {
  getAuthorizationCode(): void;

  getRefreshToken(): Observable<Token>;

  revokeToken(): void;

  authenticate(code: string): Observable<boolean>;

}

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService implements GenericCrud<User>, IAuthenticationService {

  constructor(private httpClient: HttpClient, private tokenService: TokenService) {
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

  authenticate(code: string): Observable<boolean> {
    const options = {headers: {'Content-Type': 'application/x-www-form-urlencoded'}};
    const payload = new HttpParams()
      .append(GRANT_TYPE, environment.grantType)
      .append(CODE, code)
      .append(REDIRECT_URI, environment.oauthCallbackUrl)
      .append(CLIENT_ID, environment.oauthClientId)
      .append(CLIENT_SECRET, environment.oauthClientSecret);

    return this.httpClient.post<Token>(environment.oauthTokenUrl, payload, options).pipe(
      map((token) => this.tokenService.save(token)));
  }

  getAuthorizationCode(): void {
    window.location.href = `${environment.oauthAuthorizeUrl}?
      client_id=${environment.oauthClientId}
      &
      redirect_uri=${environment.oauthCallbackUrl}
      &
      scope=${environment.scope}
      &
      response_type=${environment.responseType}`.replaceAll(" ", "");
  }

  getRefreshToken(): Observable<Token> {
    return EMPTY;
  }

  revokeToken(): void {
  }
}
