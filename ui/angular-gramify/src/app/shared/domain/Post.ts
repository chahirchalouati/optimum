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

export interface Indexable {
  index: number;
}

export type Orientation = 'PORTRAIT' | 'LANDSCAPE';

export interface Attachment extends Indexable {
  etag: string;
  name: string;
  bucket: string;
  contentType: string;
  version: string;
  orientation: Orientation;
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
