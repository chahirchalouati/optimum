import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../../environment/environment";
import {StorageService} from "../../services/storage.service";
import {EMPTY, switchMap} from "rxjs";

export interface Token {
  access_token: string;
  refresh_token: string;
  scope: string;
  id_token: string;
  token_type: string;
  expires_in: number;
}

export const TOKEN_STORE_KEY: string = "TOKEN";

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.scss']
})
export class AuthenticationComponent implements OnInit {

  constructor(
    private httpClient: HttpClient,
    private activateRoute: ActivatedRoute,
    private storageService: StorageService,
    private router: Router) {

  }

  ngOnInit(): void {
    this.activateRoute.queryParams.subscribe(params => {
      const code = params?.['code'] as string;
      if (code) {
        this.getAccessToken(code);
        return;
      }
      window.location.href = 'http://auth-server:9000/oauth2/authorize?client_id=ui-client&redirect_uri=http://127.0.0.1:4200/authorized&scope=openid&response_type=code'
    })
  }

  private getAccessToken(code: string) {
    const payload = new HttpParams()
      .append('grant_type', 'authorization_code')
      .append('code', code)
      .append('redirect_uri', "http://127.0.0.1:4200/authorized")
      .append('client_id', "ui-client")
      .append('client_secret', "secret");

    this.httpClient.post<Token>(environment.oauthTokenUrl, payload, {
      headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    }).pipe(
      switchMap((token) => {
        if (token.access_token) {
          this.storageService.setItem(TOKEN_STORE_KEY, token);
          return this.httpClient.get("http://localhost:9999/audit/audits");
        }
        return EMPTY;
      })
    ).subscribe(value => console.log(value));
  }
}
