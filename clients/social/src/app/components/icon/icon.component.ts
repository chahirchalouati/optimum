import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {icons, IconType} from "../../utils/IconUtils";

@Component({
  selector: 'app-icon',
  templateUrl: './icon.component.html',
  styleUrls: ['./icon.component.scss']
})
export class IconComponent implements OnChanges, OnInit {

  @Input() icon!: IconType;
  @Input() size: number = 20;
  @Input() width: number = this.size || 10;
  @Input() height: number = this.size || 10;
  @Input() rounded: "full" | "medium" | "small" | "none" = "none";
  content: string = '';


  ngOnInit(): void {
    this.content = icons[this.icon];
  }

  ngOnChanges(changes: SimpleChanges): void {

  }

  getRadius(rounded: "full" | "medium" | "small" | "none") {
    switch (rounded) {
      case "full":
        return 50;
      case "medium":
        return 25;
      case "small":
        return 10;
      case "none":
        return 0;
      default:
        return 0;
    }
  }
}
