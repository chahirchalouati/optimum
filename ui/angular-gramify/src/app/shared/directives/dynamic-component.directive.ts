import {ComponentFactoryResolver, Directive, HostListener, Input, ViewContainerRef} from '@angular/core';
import {ModalComponent} from "../../components/common/modal/modal.component";

@Directive({
  selector: '[appDynamicComponent]'
})
export class DynamicComponentDirective {
  @Input() component!: ModalComponent;

  constructor(private viewContainerRef: ViewContainerRef, private factory: ComponentFactoryResolver) {
  }

  @HostListener('document:click')
  public onClick() {
  }
}

