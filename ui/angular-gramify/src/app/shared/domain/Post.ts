import Selectable = Pageable.Selectable;
import {Pageable} from "./Pageable";
import Profile from "./Profile";

export type Pair = {
  left: {
    name: string;
    label: string;
    width: number;
    height: number;
  },
  right: Attachment
};

export interface Attachment {
  etag: string;
  name: string;
  bucket: string;
  contentType: string;
  version: string;
  additionalData: { [key: string]: Pair };
}

export default interface Post extends Selectable {
  attachments: Attachment[];
  createdAt: string;
  content: string;
  owner: Profile;
  visibility: Visibility;
}
export type Visibility = "PUBLIC" | "FRIEND" | "PRIVATE";
