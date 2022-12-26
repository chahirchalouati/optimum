import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Pageable} from "../shared/domain/Pageable";
import GenericCrud from "../shared/domain/GenericCrud";
import Audit from "../shared/domain/Audit";
import {EMPTY, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuditService implements GenericCrud<Audit> {

  constructor(private httpClient: HttpClient) {
  }

  delete(paylaod: Audit): Observable<void>;
  delete(id: string | number): Observable<void>;
  delete(paylaod: Audit | string | number): Observable<void> {
    return EMPTY;
  }

  get(pageRequest: Pageable.PageRequest): Observable<Pageable.Page<Audit>> {
    return EMPTY;
  }

  getId(id: string | number): Observable<Pageable.Page<Audit>> {
    return EMPTY;
  }

  post(paylaod: Audit): Observable<Audit> {
    return EMPTY;
  }

  put(paylaod: Audit, id: string | number): Observable<Audit> {
    return EMPTY;
  }

}
