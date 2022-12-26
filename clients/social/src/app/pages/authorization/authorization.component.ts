import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "../../services/authentication.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-authorization',
  templateUrl: './authorization.component.html',
  styleUrls: ['./authorization.component.scss']
})
export class AuthorizationComponent implements OnInit, OnDestroy {
  authenticateSubscription!: Subscription;

  constructor(private httpClient: HttpClient,
              private activatedRoute: ActivatedRoute,
              private readonly router: Router,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      if (params['code']) {
        this.authenticateSubscription = this.authenticationService.getAccessToken(params['code'] as string)
          .subscribe(({isAuthenticated, token}) => {
            if (isAuthenticated) {
              // call routeHandler for more routing customization
              this.router.navigate(['']);
            }
          });
      } else {
        this.authenticationService.getAuthorizationCode();
      }
    })

  }

  ngOnDestroy(): void {
    this.authenticateSubscription.unsubscribe();
  }
}
