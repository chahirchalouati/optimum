import {Component, Input} from '@angular/core';

export type ButtonKind = 'flat' | 'rounded' | 'transparent' | string;
export type ButtonType = 'submit' | 'button' | 'reset'

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.scss']
})
export class ButtonComponent {
  @Input() kind: ButtonKind = 'rounded';
  @Input() type: ButtonType = 'button';
  @Input() label!: string;

}
