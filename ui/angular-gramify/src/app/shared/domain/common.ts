import {Attachment} from "./Attachment";

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

export type Visibility = "PUBLIC" | "FRIEND" | "PRIVATE";
