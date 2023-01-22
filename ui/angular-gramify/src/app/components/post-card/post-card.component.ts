import {Component, Input} from '@angular/core';
import Post, {Visibility} from "../../shared/domain/Post";
import moment from "moment/moment";
import {IconType} from "../../utils/IconUtils";

@Component({
  selector: 'app-post-card',
  templateUrl: './post-card.component.html',
  styleUrls: ['./post-card.component.scss']
})
export class PostCardComponent {
  @Input() post!: Post;

  getDate(createdAt: string) {
    return moment(createdAt, "YYYY-MM-DD HH-mm-ss").fromNow(true);
  }

  getVisibilityIcon(visibility: Visibility): IconType {
    return visibility.toLowerCase() as IconType;
  }
}
