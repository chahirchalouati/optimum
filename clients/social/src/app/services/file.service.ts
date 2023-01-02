import {Injectable} from '@angular/core';
import Post from "../shared/domain/Post";
import {HttpClient} from "@angular/common/http";
import {Pageable} from "../shared/domain/Pageable";
import {Observable} from "rxjs";
import {environment} from "../../environment/environment";

@Injectable({providedIn: 'root'})
export class FileService {

  constructor(private httpClient: HttpClient) {
  }

  get(name: string, etag: string): Observable<Pageable.Page<Post>> {
    return this.httpClient.get<any>(environment.api.file.FILES_GET_ONE + "/" + name + "/" + etag);
  }

}
