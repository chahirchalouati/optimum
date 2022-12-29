import {Pageable} from "./Pageable";
import Selectable = Pageable.Selectable;

export default interface Profile extends Selectable {
  id: string;
  publisher: string;
  title: string;
}
