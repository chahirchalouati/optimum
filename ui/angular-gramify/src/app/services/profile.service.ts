import {Injectable} from '@angular/core';
import GenericCrud from "../shared/domain/GenericCrud";
import Profile from "../shared/domain/Profile";
import {HttpClient} from "@angular/common/http";
import {Pageable} from "../shared/domain/Pageable";
import {Observable} from "rxjs";
import {environment} from "../../environment/environment";
import {StorageService} from "./storage.service";

export const KEY_PROFILE: string = 'PROFILE';

@Injectable({
  providedIn: 'root'
})
export class ProfileService implements GenericCrud<Profile> {


  constructor(private httpClient: HttpClient, private storageService: StorageService) {
  }

  get(pageRequest: Pageable.PageRequest): Observable<Pageable.Page<Profile>> {
    return this.httpClient.get<Pageable.Page<Profile>>(environment.api.profile.PROFILE_GET_PAGE, {params: {...pageRequest}});
  }

  getId(id: string | number): Observable<Pageable.Page<Profile>> {
    return this.httpClient.get<Pageable.Page<Profile>>(environment.api.profile.PROFILE_GET_BY_ID, {params: {id}});
  }

  post(payload: Profile): Observable<Profile> {
    return this.httpClient.get<Profile>(environment.api.profile.PROFILE_POST);
  }

  put(payload: Profile, id: string | number): Observable<Profile> {
    return this.httpClient.put<Profile>(environment.api.profile.PROFILE_PUT, payload, {params: {id}});
  }

  delete(id: string | number): Observable<Profile> {
    return this.httpClient.delete<Profile>(environment.api.profile.PROFILE_DELETE, {params: {id}});
  }

  getUserProfile(username: string): Observable<Profile> {
    return this.httpClient.get<Profile>(`${environment.api.profile.PROFILE_GET_USER}`, {params: {username: username}});
  }

  setProfile(profile: Profile): Profile {
    this.storageService.with("localStorage")
      .setItem(KEY_PROFILE, profile);
    return profile;
  }

  getProfile(): Profile {
    return this.storageService.with("localStorage")
      .getItem<Profile>(KEY_PROFILE);
  }

}
