import {Component, Input, TemplateRef} from '@angular/core';

@Component({template: ''})
export abstract class ModalComponent {
  @Input() title: string = '';
  @Input() show: boolean = false;
  @Input() showGoBack: boolean = false;
  @Input() showClose: boolean = false;
  @Input() clickOutsideClose: boolean = false;
  @Input() content!: TemplateRef<any>;

  closeModal(): void {
    this.show = false;
  }

}
