import {Pageable} from "./Pageable";
import Selectable = Pageable.Selectable;

export default interface User extends Selectable {
  id: string;
  publisher: string;
  title: string;
}
