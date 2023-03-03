import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Pageable} from "../shared/domain/Pageable";
import {Observable} from "rxjs";
import Comment from "../shared/domain/Comment";
import {environment} from "../../environment/environment";

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private httpClient: HttpClient) {
  }

  get(id: number, pageRequest: Pageable.PageRequest): Observable<Pageable.Page<Comment>> {
    return this.httpClient.get<Pageable.Page<Comment>>(environment.api.comment.COMMENT_GET_PAGE + "/" + id, {
      params: {
        ...pageRequest
      }
    });
  }

  getId(id: string | number): Observable<Pageable.Page<Comment>> {
    return this.httpClient.get<Pageable.Page<Comment>>(environment.api.comment.COMMENT_GET_BY_ID, {params: {id}});
  }

  post(payload: Comment): Observable<Comment> {
    return this.httpClient.get<Comment>(environment.api.comment.COMMENT_POST);
  }

  put(payload: Comment, id: string | number): Observable<Comment> {
    return this.httpClient.put<Comment>(environment.api.comment.COMMENT_PUT, payload, {params: {id}});
  }

  delete(id: string | number): Observable<Comment> {
    return this.httpClient.delete<Comment>(environment.api.comment.COMMENT_DELETE, {params: {id}});
  }
}
