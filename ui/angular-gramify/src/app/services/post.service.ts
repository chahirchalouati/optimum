import {Injectable} from '@angular/core';
import GenericCrud from "../shared/domain/GenericCrud";
import Post from "../shared/domain/Post";
import {HttpClient} from "@angular/common/http";
import {Page, PageRequest} from "../shared/domain/Pageable";
import {Observable} from "rxjs";
import {environment} from "../../environment/environment";

@Injectable({providedIn: 'root'})
export class PostService implements GenericCrud<Post> {

  constructor(private httpClient: HttpClient) {}

  get(pageRequest: PageRequest): Observable<Page<Post>> {
    return this.httpClient.get<Page<Post>>(environment.api.post.POST_GET_PAGE, {params: {...pageRequest}});
  }

  getId(id: string | number): Observable<Page<Post>> {
    return this.httpClient.get<Page<Post>>(environment.api.post.POST_GET_BY_ID, {params: {id}});
  }

  post(payload: Post): Observable<Post> {
    return this.httpClient.get<Post>(environment.api.post.POST_POST);
  }

  put(payload: Post, id: string | number): Observable<Post> {
    return this.httpClient.put<Post>(environment.api.post.POST_PUT, payload, {params: {id}});
  }

  delete(id: string | number): Observable<Post> {
    return this.httpClient.delete<Post>(environment.api.post.POST_DELETE, {params: {id}});
  }
}
