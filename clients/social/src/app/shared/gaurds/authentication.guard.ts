import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {TokenService} from "../../services/token.service";
import {AuthenticationService} from "../../services/authentication.service";

type GuardType = Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree;

@Injectable({
  providedIn: 'root'
})
export class AuthenticationGuard implements CanActivate {
  constructor(private tokenService: TokenService,
              private authenticationService: AuthenticationService,
              private readonly router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): GuardType {
    if (!this.tokenService.isAuthenticated()) {
      this.authenticationService.getAuthorizationCode();
    }
    return true;
  }

}
