import {Pageable} from "./Pageable";
import {ObjectsUtils} from "../../utils/ObjectsUtils";
import Selectable = Pageable.Selectable;
import isNotEmpty = ObjectsUtils.isNotEmpty;

export default interface Profile extends Selectable {
  username: string;
  email: string;
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
    const fullName = this.firstName + " " + this.lastName;
    return isNotEmpty(fullName) ? fullName : '';
  }
}


