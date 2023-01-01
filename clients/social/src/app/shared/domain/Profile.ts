import {Pageable} from "./Pageable";
import Selectable = Pageable.Selectable;

export default interface Profile extends Selectable {
  avatar: string;
  backgroundImage: string;
  user: User;
}


export interface Role {
  id: string;
  enabled: boolean;
  name: string;
  permissions: any[];
}

export class User {
  fullName!: string;
  firstName!: string;
  lastName!: string;
  username!: string;
  email!: string;
  roles!: Role[];
  enabled!: boolean;
  accountNonLocked!: boolean;
  accountNonExpired!: boolean;

  constructor(obj: {}) {
    Object.assign(this, obj);
  }

  get getFullName() {
    return this.firstName + " " + this.lastName;
  }
}


