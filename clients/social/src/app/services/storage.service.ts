import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  with(kind: "localStorage" | "sessionStorage") {
    return this.StorageImp(kind);
  }

  StorageImp(kind: "localStorage" | "sessionStorage") {
    return class {
      private static storage: Storage = kind === "localStorage" ? localStorage : sessionStorage;

      static setItem<T>(key: string, value: T): T {
        this.storage.setItem(key, JSON.stringify(value));
        return value;
      }

      static getItem<T>(key: string): T {
        const item = this.storage.getItem(key);
        return !!item ? JSON.parse(item) as T : null as T;
      }

      static removeItem(key: string) {
        this.storage.removeItem(key);

      }

      static clear() {
        return this.storage.clear();
      }
    }
  }
}
