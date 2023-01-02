import {Pipe, PipeTransform} from '@angular/core';
import {Observable, tap} from "rxjs";

import {FileService} from "../../services/file.service";

@Pipe({name: 'src'})
export class SrcPipe implements PipeTransform {
  constructor(private readonly service: FileService) {
  }

  transform(name: string, etag: string): Observable<any> {
    return this.service.get(name, etag).pipe(tap(value => console.log(value)));
  }
}
