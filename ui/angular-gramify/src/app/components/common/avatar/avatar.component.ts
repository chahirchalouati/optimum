import {Component, Input} from '@angular/core';

export type Shape = 'rounded' | 'flat' | 'none';

@Component({
  selector: 'app-avatar',
  templateUrl: './avatar.component.html',
  styleUrls: ['./avatar.component.scss']
})
export class AvatarComponent {
  @Input() img: string | undefined = ''
  @Input() size: number = 30;
  @Input() shape: Shape = "none";
  @Input() isClickable: boolean = true;

}
