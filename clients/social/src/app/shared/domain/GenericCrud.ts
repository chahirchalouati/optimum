import {Pageable} from "./Pageable";
import {Observable} from "rxjs";
import PageRequest = Pageable.PageRequest;
import Page = Pageable.Page;
import Selectable = Pageable.Selectable;

export default interface GenericCrud<T extends Selectable> {
  get(pageRequest: PageRequest): Observable<Page<T>>;

  getId(id: string | number): Observable<Page<T>>;

  post(paylaod: T): Observable<T>;

  put(paylaod: T, id: string | number): Observable<T>;

  delete(paylaod: T): Observable<void>;

  delete(id: string | number): Observable<void>;
}
