import {Pageable} from "./Pageable";
import Selectable = Pageable.Selectable;

export default interface Profile extends Selectable {
  email: string;
  avatar: string;
  backgroundImage: string;
  user: Partial<User>;
}


export interface Role {
  id: string;
  enabled: boolean;
  name: string;
  permissions: any[];
}

export interface User {
  fullName: string;
  firstName: string;
  lastName: string;
  username: string;
  email: string;
  roles: Role[];
  enabled: boolean;
  accountNonLocked: boolean;
  accountNonExpired: boolean;
}


