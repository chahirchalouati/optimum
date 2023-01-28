import {Directive, EventEmitter, HostListener, Output} from '@angular/core';

@Directive({
  selector: '[appModal]'
})
export class ModalDirective {
  @Output() openModal = new EventEmitter<boolean>(false);

  constructor() {
  }

  @HostListener('document:click')
  public onClick() {
    this.openModal.emit(true);
  }

}
