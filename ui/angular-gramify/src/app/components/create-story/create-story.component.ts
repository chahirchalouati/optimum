import {Component, Input} from '@angular/core';
import Profile from "../../shared/domain/Profile";

@Component({
  selector: 'app-create-story',
  templateUrl: './create-story.component.html',
  styleUrls: ['./create-story.component.scss']
})
export class CreateStoryComponent {
  @Input() profile!: Profile;

}
