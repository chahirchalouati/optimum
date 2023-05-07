import {Indexable, Orientation, Pair} from "./common";

export interface Attachment extends Indexable {
  etag: string;
  name: string;
  bucket: string;
  contentType: string;
  version: string;
  orientation: Orientation;
  additionalData: { [key: string]: Pair };
}
