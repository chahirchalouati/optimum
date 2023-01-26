import Selectable = Pageable.Selectable;
import {Pageable} from "./Pageable";
import Profile from "./Profile";

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
  owner?: Profile;
  visibility: Visibility;
}
export type Visibility = "PUBLIC" | "FRIEND" | "PRIVATE";
