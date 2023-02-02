import {Component, Input} from '@angular/core';
import {DropDownBoxComponent} from "../../common/drop-down-box/drop-down-box.component";
import Profile from "../../../shared/domain/Profile";
import {EMPTY, Observable} from "rxjs";
import {environment} from "../../../../environment/environment";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  @Input() profile$: Observable<Profile> = EMPTY;
  appName: string = environment.appName;

  constructor() {}

  alert($event: MouseEvent) {
    alert($event.timeStamp)
  }

  getResult(value: string, dropDownBoxComponent: DropDownBoxComponent) {
    dropDownBoxComponent.show = value.length > 3;
    dropDownBoxComponent.showDefaultMessage = value.length < 3;
  }
}
