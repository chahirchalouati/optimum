import {Selectable} from "./Pageable";

export default interface User extends Selectable {
  id: string;
  publisher: string;
  title: string;
}
