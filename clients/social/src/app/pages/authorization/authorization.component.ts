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
  hasCode: boolean = false;

  constructor(private httpClient: HttpClient,
              private activatedRoute: ActivatedRoute,
              private tokenService: TokenService,
              private router: Router,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(
      params => {
        if (this.tokenService.isAuthenticated()) {
          this.router.navigate(['/home']);
          return;
        }
        const code = params['code'] as string;
        if (isNotEmpty(code)) {
          this.hasCode = true;
          this.authenticateSubscription = this.authenticationService.authenticate(code).subscribe(
            isAuthenticated => {
              if (isAuthenticated) {
                this.router.navigate(['/home']);
              } else {
                this.router.navigate(['error']);
              }
            },
            () => {
              if (this.tokenService.tokenExists()) {
                this.tokenService.clear()
                this.router.navigate(['']);
              }
            }
          )
        } else {
          this.authenticationService.getAuthorizationCode();
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
