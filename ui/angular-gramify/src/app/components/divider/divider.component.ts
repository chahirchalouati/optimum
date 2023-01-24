import {Component, Input} from '@angular/core';

export type Direction = "horizontal" | "vertical";

@Component({
  selector: 'app-divider',
  templateUrl: './divider.component.html',
  styleUrls: ['./divider.component.scss']
})
export class DividerComponent {
  @Input() direction: Direction = "horizontal";
  @Input() thickness: number = 1;
}
