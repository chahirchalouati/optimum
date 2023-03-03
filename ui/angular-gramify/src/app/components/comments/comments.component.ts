import {Component, Input} from '@angular/core';
import {CommentService} from "../../services/comment.service";
import {BehaviorSubject, map, mergeMap, Observable, scan, tap} from "rxjs";
import Comment from "../../shared/domain/Comment";
import {CommonProperties} from "../common-properties.component";


@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.scss']
})
export class CommentsComponent extends CommonProperties {
  @Input() posId!: number;
  pageSize = 5;
  initialPage = 0;
  isLast: boolean = false;
  pageSubject = new BehaviorSubject<number>(this.initialPage);
  currentPage$: Observable<number> = this.pageSubject.asObservable();
  comments$: Observable<Comment[]> = this.currentPage$.pipe(
    tap(() => this.isLoading = true),
    mergeMap(page => this.getComments(this.posId, page)),
    tap(page => this.isLast = page.last),
    map(({content}) => content),
    scan((acc: Comment[], content: Comment[]) => acc.concat(content), []),
    tap(() => this.isLoading = false),
  );


  constructor(private commentService: CommentService) {
    super();
  }

  getComments(posId: number, page: number) {
    return this.commentService.get(posId, {page, size: this.pageSize})

  }
}
