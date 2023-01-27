import {Component, Input} from '@angular/core';
import {Observable} from "rxjs";
import Profile from "../../shared/domain/Profile";

@Component({
  selector: 'app-card-story',
  templateUrl: './card-story.component.html',
  styleUrls: ['./card-story.component.scss']
})
export class CardStoryComponent {
  @Input() isForm: boolean = false;
  @Input() profile!: Observable<Profile>;

}
