import {Component, OnInit} from '@angular/core';
import {AuditService} from "../../services/audit.service";
import {EMPTY, Observable} from "rxjs";
import {ProfileService} from "../../services/profile.service";
import {TokenService} from "../../services/token.service";
import Profile from 'src/app/shared/domain/Profile';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  profile$: Observable<Profile> = EMPTY;
  userInfo: any = this.tokenService.getUserInfo();

  constructor(private auditService: AuditService, private profileService: ProfileService, private tokenService: TokenService) {
  }

  ngOnInit(): void {
    this.profile$ = this.profileService.getUserProfile(this.userInfo.username);
  }

}
