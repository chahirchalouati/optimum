import Selectable = Pageable.Selectable;
import {Pageable} from "./Pageable";
import Profile from "./Profile";
import {Attachment} from "./Attachment";
import {Visibility} from "./common";


export default interface Post extends Selectable {
  id: number;
  images: Attachment[];
  videos: Attachment[];
  createdAt: string;
  content: string;
  profile: Profile;
  visibility: Visibility;
  commentsCount: number;
  likesCount: number;
  shareCount: number;
}



