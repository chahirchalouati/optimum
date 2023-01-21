import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-drop-down-box',
  templateUrl: './drop-down-box.component.html',
  styleUrls: ['./drop-down-box.component.scss']
})
export class DropDownBoxComponent {
  @Input() show: boolean = false;
  @Input() showDefaultMessage: boolean = false;
  @Input() defaultMessage!: string;

}
