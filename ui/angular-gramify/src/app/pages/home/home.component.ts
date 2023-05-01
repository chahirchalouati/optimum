import {Component, OnInit} from '@angular/core';
import {AuditService} from "../../services/audit.service";
import {EMPTY, finalize, map, Observable, zip} from "rxjs";
import {ProfileService} from "../../services/profile.service";
import {TokenService} from "../../services/token.service";
import Profile from 'src/app/shared/domain/Profile';

import Post from "../../shared/domain/Post";
import {PostService} from "../../services/post.service";
import moment from "moment/moment";
import {Page, Sortable} from "../../shared/domain/Pageable";

export interface State {
  profile: Profile,
  posts: Page<Post>
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  initialState$: Observable<State> = EMPTY;
  userInfo: any = this.tokenService.getUserInfo();
  isLoading: boolean = false;

  constructor(private auditService: AuditService,
              private profileService: ProfileService,
              private postService: PostService,
              private tokenService: TokenService) {}

  ngOnInit(): void {
    this.isLoading = true;
    this.initialState$ = zip(
      this.profileService.getUserProfile(this.userInfo.username),
      this.postService.get({sort: [Sortable.with('createDate', "DESC")]}))
      .pipe(
        map(([profile, posts]) => ({profile, posts})),
        finalize(() => this.isLoading = false)
      );
  }

  hasContent(type: string, contentType: string) {
    return contentType.concat(type);
  }

  autoPlayVideo($event: boolean, videoElement: HTMLVideoElement) {
    if ($event) {
      videoElement.play()
    } else {
      videoElement.pause();
    }
  }

  getDate(createdAt: string): string {
    return moment().from(createdAt, true);
  }
}
