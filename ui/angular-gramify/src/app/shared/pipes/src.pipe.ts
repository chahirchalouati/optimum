import {Pipe, PipeTransform} from '@angular/core';
import {Observable} from "rxjs";
import {FileService} from "../../services/file.service";

@Pipe({name: 'src'})
export class SrcPipe implements PipeTransform {
  constructor(private readonly fileService: FileService) {
  }

  transform(name: string, etag: string): Observable<any> {
    return this.fileService.get(name, etag);
  }
}
