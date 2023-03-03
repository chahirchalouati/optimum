import Profile from "./Profile";
import {Pageable} from "./Pageable";
import {Attachment} from "./Attachment";
import {Visibility} from "./common";
import Selectable = Pageable.Selectable;

export default interface Comment extends Selectable {
  id: number;
  attachments: Attachment[];
  createdAt: string;
  content: string;
  profile: Profile;
  visibility: Visibility;
}
