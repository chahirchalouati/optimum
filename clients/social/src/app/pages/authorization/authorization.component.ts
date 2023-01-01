import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "../../services/authentication.service";
import {Subscription} from "rxjs";
import {TokenService} from "../../services/token.service";
import {ObjectsUtils} from "../../utils/ObjectsUtils";
import isNotEmpty = ObjectsUtils.isNotEmpty;

@Component({
  selector: 'app-authorization',
  templateUrl: './authorization.component.html',
  styleUrls: ['./authorization.component.scss']
})
export class AuthorizationComponent implements OnInit, OnDestroy {
  authenticateSubscription!: Subscription;

  constructor(private httpClient: HttpClient,
              private activatedRoute: ActivatedRoute,
              private tokenService: TokenService,
              private router: Router,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(
      params => {
        const code = params['code'] as string;
        if (isNotEmpty(code)) {
          this.authenticateSubscription = this.authenticationService.authenticate(code).subscribe(
            isAuthenticated => {
              if (isAuthenticated) {
                this.router.navigate(['']);
              } else {
                this.router.navigate(['error']);
              }
            }
          )
        }
      }
    )
  }

  ngOnDestroy(): void {
    if (this.authenticateSubscription) {
      this.authenticateSubscription.unsubscribe();
    }
  }
}
