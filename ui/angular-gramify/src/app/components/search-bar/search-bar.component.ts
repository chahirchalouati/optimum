import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.scss']
})
export class SearchBarComponent {
  @Input() placeholder: string = 'search gramify';
  @Output() keysUpEvent: EventEmitter<any> = new EventEmitter<any>();

  keyUpEventChange($event: Event) {
    this.keysUpEvent.emit(($event.currentTarget as HTMLInputElement).value)
  }
}
