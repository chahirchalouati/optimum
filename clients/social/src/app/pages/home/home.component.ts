import {Component, OnInit} from '@angular/core';
import {AuditService} from "../../services/audit.service";
import {EMPTY, Observable} from "rxjs";
import {ProfileService} from "../../services/profile.service";
import {TokenService} from "../../services/token.service";
import Profile from 'src/app/shared/domain/Profile';
import {environment} from "../../../environment/environment";
import {Pageable} from "../../shared/domain/Pageable";
import Post from "../../shared/domain/Post";
import {PostService} from "../../services/post.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  profile$: Observable<Profile> = EMPTY;
  posts$: Observable<Pageable.Page<Post>> = EMPTY;
  userInfo: any = this.tokenService.getUserInfo();
  logo: string = environment.logo;

  constructor(private auditService: AuditService,
              private profileService: ProfileService,
              private postService: PostService,
              private tokenService: TokenService) {
  }

  ngOnInit(): void {
    this.profile$ = this.profileService.getUserProfile(this.userInfo.username);
    this.posts$ = this.postService.get({});
  }
}
