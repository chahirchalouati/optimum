import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {StorageService} from "./storage.service";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  ACCESS_TOKEN_KEY_NAME = 'access_token';
  REFRESH_TOKEN_KEY_NAME = 'refresh_token';
  expired = false

  constructor(private http: HttpClient,
              private router: Router,
              private storageService: StorageService
  ) {
  }

  getAuthorizationCode() {
    window.location.href =
      'http://auth-server:9000/oauth2/authorize?client_id=ui-client&redirect_uri=http://127.0.0.1:4200/authorized&scope=openid&response_type=code';
  }

  getAccessToken(code: string) {
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/x-www-form-urlencoded");
    myHeaders.append("Cookie", "JSESSIONID=3B862ABE038251BD399E87ED7043CDC2");

    const urlencoded = new URLSearchParams();
    urlencoded.append("code", code);
    urlencoded.append("client_id", "ui-client");
    urlencoded.append("client_secret", "secret");
    urlencoded.append("grant_type", "authorization_code");
    urlencoded.append("redirect_uri", "http://127.0.0.1:4200/authorized");

    const requestOptions = {

      method: 'POST',
      headers: myHeaders,
      body: urlencoded,
      redirect: 'follow'
    };

    // @ts-ignore
    // fetch("http://auth-server:9000/oauth2/token", requestOptions)
    //   .then(response => response.text())
    //   .then(result => console.log(result))
    //   .catch(error => console.log('error', error));
  }

  isAuthenticated()
    :
    Observable<boolean> {
    const token = this.storageService
      .getItem<string>(this.ACCESS_TOKEN_KEY_NAME);
    if (!
      this.isNotExpired(token)
    ) {
      this.getAuthorizationCode()
    }

    return of(true)

  }

  isNotExpired(token
                 :
                 string
  ) {
    return this.expired;
  }
}
