import {Component, OnInit} from '@angular/core';
import {DropDownBoxComponent} from "../../common/drop-down-box/drop-down-box.component";
import Profile from "../../../shared/domain/Profile";
import {EMPTY, Observable} from "rxjs";
import {ProfileService} from "../../../services/profile.service";
import {AuditService} from "../../../services/audit.service";
import {TokenService} from "../../../services/token.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  profile$: Observable<Profile> = EMPTY;
  userInfo: any = this.tokenService.getUserInfo();

  constructor(private auditService: AuditService,
              private profileService: ProfileService,
              private tokenService: TokenService) {
  }

  ngOnInit(): void {
    this.profile$ = this.profileService.getUserProfile(this.userInfo.username);
  }

  alert($event: MouseEvent) {
    alert($event.timeStamp)
  }

  getResult(value: string, dropDownBoxComponent: DropDownBoxComponent) {
    dropDownBoxComponent.show = value.length > 3;
    dropDownBoxComponent.showDefaultMessage = value.length < 3;
  }
}
