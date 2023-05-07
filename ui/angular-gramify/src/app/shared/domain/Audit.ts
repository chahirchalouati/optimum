import {Selectable} from "./Pageable";


export default interface Audit extends Selectable {
  id: string;
  publisher: string;
  title: string;
}
