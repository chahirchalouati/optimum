import {Component, Input, OnInit} from '@angular/core';
import Post, {Visibility} from "../../../shared/domain/Post";
import moment from "moment/moment";
import {IconType} from "../../../utils/IconUtils";
import {environment} from "../../../../environment/environment";

@Component({
  selector: 'app-post-card',
  templateUrl: './post-card.component.html',
  styleUrls: ['./post-card.component.scss']
})
export class PostCardComponent implements OnInit {
  @Input() post!: Post;
  shouldShowMore: boolean = false;


  ngOnInit(): void {
    this.shouldShowMore = this.post.content.length < environment.maxContentSize;
  }

  getDate(createdAt: string) {
    return moment(createdAt, "YYYY-MM-DD HH-mm-ss").fromNow(true);
  }

  getVisibilityIcon(visibility: Visibility): IconType {
    return visibility.toLowerCase() as IconType;
  }
}
