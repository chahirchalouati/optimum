import Profile from "./Profile";
import {Selectable} from "./Pageable";
import {Attachment} from "./Attachment";
import {Visibility} from "./common";

export default interface Comment extends Selectable {
  id: number;
  attachments: Attachment[];
  createdAt: string;
  content: string;
  profile: Profile;
  visibility: Visibility;
}
