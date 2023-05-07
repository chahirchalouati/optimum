import {Component, Input} from '@angular/core';
import Profile from "../../domain/Profile";

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent {
  @Input() profile!: Profile;
}
