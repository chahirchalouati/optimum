import {Pageable} from "./Pageable";
import Selectable = Pageable.Selectable;

export default interface Audit extends Selectable {
  id: string;
  publisher: string;
  title: string;
}
