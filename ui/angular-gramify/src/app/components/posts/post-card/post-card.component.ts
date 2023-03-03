import {Component, Input, OnInit} from '@angular/core';
import Post from "../../../shared/domain/Post";
import moment from "moment/moment";
import {IconType} from "../../../utils/IconUtils";
import {environment} from "../../../../environment/environment";
import {User} from "../../../shared/domain/Profile";
import {CommentService} from "../../../services/comment.service";
import {Visibility} from "../../../shared/domain/common";

@Component({
  selector: 'app-post-card',
  templateUrl: './post-card.component.html',
  styleUrls: ['./post-card.component.scss']
})
export class PostCardComponent implements OnInit {
  @Input() post!: Post;
  shouldShowMore: boolean = false;
  shouldShowComments: boolean = false;


  constructor(private commentService: CommentService) {
  }

  ngOnInit(): void {
    this.shouldShowMore = this.post.content.length < environment.maxContentSize;
  }

  getDate(createdAt: string) {
    return moment(createdAt, "YYYY-MM-DD HH-mm-ss").fromNow(true);
  }

  getVisibilityIcon(visibility: Visibility): IconType {
    return visibility.toLowerCase() as IconType;
  }

  getFullName(user: User | undefined) {
    return !!user ? user.fullName : "";
  }

  toggle() {
    this.shouldShowMore = !this.shouldShowMore;
  }
}
