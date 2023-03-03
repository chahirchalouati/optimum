import {Component, Input} from '@angular/core';

@Component({template: ''})
export abstract class CommonProperties {
  @Input() isLoading: boolean = false;
  @Input() show: boolean = false;
  @Input() hide: boolean = false;
}
