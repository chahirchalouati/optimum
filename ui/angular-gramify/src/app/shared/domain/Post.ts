import Selectable = Pageable.Selectable;
import {Pageable} from "./Pageable";
import Profile from "./Profile";
import {Attachment} from "./Attachment";
import {Visibility} from "./common";


export default interface Post extends Selectable {
  id: number;
  attachments: Attachment[];
  createdAt: string;
  content: string;
  owner: Profile;
  visibility: Visibility;
}



