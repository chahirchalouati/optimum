import Selectable = Pageable.Selectable;
import {Pageable} from "./Pageable";

export interface Attachment {
  etag: string;
  name: string;
  bucket: string;
  version: string;
}

export default interface Post extends Selectable {
  attachments: Attachment[];
  createdAt: string;
  content: string;
  username?: any;
}
