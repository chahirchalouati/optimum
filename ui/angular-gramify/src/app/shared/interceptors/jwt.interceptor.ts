import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TokenService} from "../../services/token.service";
import {AuthenticationService} from "../../services/authentication.service";

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private tokenService: TokenService,
              private authenticationService: AuthenticationService) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (request.url.indexOf("oauth2/token") === 0) {
      return next.handle(request);
    }
    request = request.clone({
      setHeaders: {Authorization: this.tokenService.isAuthenticated() ? `Bearer ${this.tokenService.get().access_token}` : ''}
    });
    return next.handle(request);
  }

}
