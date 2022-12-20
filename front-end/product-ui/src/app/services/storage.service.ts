import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  setItem<T>(key: string, value: T): T {
    localStorage.setItem(key, JSON.stringify(value));
    return value;
  }

  getItem<T>(key: string): T {
    const item = localStorage.getItem(key);
    return !!item ? JSON.parse(item) as T : null as T;
  }

  removeItem(key: string) {
    localStorage.removeItem(key);

  }

  clear() {
    return localStorage.clear();
  }
}
