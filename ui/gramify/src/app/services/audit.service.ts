import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Pageable} from "../shared/domain/Pageable";
import GenericCrud from "../shared/domain/GenericCrud";
import Audit from "../shared/domain/Audit";
import {Observable} from "rxjs";
import {environment} from "../../environment/environment";

@Injectable({providedIn: 'root'})
export class AuditService implements GenericCrud<Audit> {

  constructor(private httpClient: HttpClient) {
  }

  get(pageRequest: Pageable.PageRequest): Observable<Pageable.Page<Audit>> {
    return this.httpClient.get<Pageable.Page<Audit>>(environment.api.audit.AUDIT_GET_PAGE, {params: {...pageRequest}});
  }

  getId(id: string | number): Observable<Pageable.Page<Audit>> {
    return this.httpClient.get<Pageable.Page<Audit>>(environment.api.audit.AUDIT_GET_BY_ID, {params: {id}});
  }

  post(payload: Audit): Observable<Audit> {
    return this.httpClient.get<Audit>(environment.api.audit.AUDIT_POST);
  }

  put(payload: Audit, id: string | number): Observable<Audit> {
    return this.httpClient.put<Audit>(environment.api.audit.AUDIT_PUT, payload, {params: {id}});
  }

  delete(id: string | number): Observable<Audit> {
    return this.httpClient.delete<Audit>(environment.api.audit.AUDIT_DELETE, {params: {id}});
  }

}
