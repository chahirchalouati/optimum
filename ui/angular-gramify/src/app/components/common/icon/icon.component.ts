import {Component, Input, OnInit} from '@angular/core';
import {icons, IconType} from "../../../utils/IconUtils";

@Component({
  selector: 'app-icon',
  templateUrl: './icon.component.html',
  styleUrls: ['./icon.component.scss']
})
export class IconComponent implements OnInit {
  @Input() type: 'flat' | 'rounded' | 'none' | "card" | string = "none";
  @Input() name!: IconType;
  @Input() size: number = 30;
  @Input() iconSize: number = 20;
  @Input() toolTipText: string = '';
  @Input() toolTipActive: boolean = false;
  svg: string = '';

  ngOnInit(): void {
    this.svg = icons[this.name]
  }

}
