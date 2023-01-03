import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {environment} from "../../environment/environment";
import {DomSanitizer} from "@angular/platform-browser";

@Injectable({providedIn: 'root'})
export class FileService {

  constructor(private httpClient: HttpClient, private domSanitizer: DomSanitizer) {
  }

  get(name: string, etag: string): Observable<MediaSource> {
    const options = {responseType: 'blob'};
    // @ts-ignore
    return this.httpClient.get<MediaSource>(environment.api.file.FILES_GET_ONE + "/" + name + "/" + etag, options)
      .pipe(
        map(e => URL.createObjectURL(new Blob([e]))),
        map(e => this.domSanitizer.bypassSecurityTrustUrl(e))
      );
  }

}
