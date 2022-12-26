import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import Token, {TOKEN_STORE_KEY} from "../domain/Token";
import {StorageService} from "../../services/storage.service";

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private storageService: StorageService) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const token = this.storageService.getItem(TOKEN_STORE_KEY) as Token;
    if (!!token?.access_token) {
      request = request.clone({
        setHeaders: {Authorization: `Bearer ${token.access_token}`}
      });
    }
    return next.handle(request);
  }

}
