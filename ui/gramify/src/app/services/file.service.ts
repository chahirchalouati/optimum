import {Injectable} from '@angular/core';
import {HttpClient, HttpContext, HttpHeaders, HttpParams} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {environment} from "../../environment/environment";
import {DomSanitizer, SafeUrl} from "@angular/platform-browser";

export interface HttpOption {
  headers?: HttpHeaders | {
    [header: string]: string | string[];
  };
  context?: HttpContext;
  observe?: 'body';
  params?: HttpParams | {
    [param: string]: string | number | boolean | ReadonlyArray<string | number | boolean>;
  };
  reportProgress?: boolean;
  responseType?: 'json' | 'blob';
  withCredentials?: boolean;
}

@Injectable({providedIn: 'root'})
export class FileService {

  constructor(private httpClient: HttpClient, private domSanitizer: DomSanitizer) {
  }

  get(name: string, etag: string): Observable<SafeUrl> {
    const options = {responseType: 'blob' as 'json'};
    return this.httpClient.get<Blob>(environment.api.file.FILES_GET_ONE + "/" + name + "/" + etag, options)
      .pipe(
        map(e => URL.createObjectURL(new Blob([e]))),
        map(e => this.domSanitizer.bypassSecurityTrustUrl(e))
      );
  }

}
