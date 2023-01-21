import {Component, Input, OnInit} from '@angular/core';
import {icons} from "../../utils/IconUtils";

@Component({
  selector: 'app-icon',
  templateUrl: './icon.component.html',
  styleUrls: ['./icon.component.scss']
})
export class IconComponent implements OnInit {
  @Input() type: 'flat' | 'rounded' | 'none' = "none";
  @Input() name!: keyof typeof icons;
  @Input() size: number = 30;
  svg: string = '';

  ngOnInit(): void {
    this.svg = icons[this.name]
  }

}
