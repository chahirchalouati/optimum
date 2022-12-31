import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import Token, {TOKEN_STORE_KEY} from "../domain/Token";
import {StorageService} from "../../services/storage.service";
import {AuthenticationService} from "../../services/authentication.service";

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private readonly storageService: StorageService,
              private readonly authenticationService: AuthenticationService) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const token = this.storageService.getItem(TOKEN_STORE_KEY) as Token;
    if (!token && request.url.indexOf("oauth2/token") === 0) {
      this.authenticationService.logOut();
    }
    if (!!token?.access_token) {
      request = request.clone({
        setHeaders: {Authorization: `Bearer ${token.access_token}`}
      });
    }
    return next.handle(request);
  }

}
