import {ComponentFactoryResolver, Directive, HostListener, Input, ViewContainerRef} from '@angular/core';
import {CreatePostModal} from "../../components/common/modal/create-post-modal.component";

@Directive({
  selector: '[appDynamicComponent]'
})
export class DynamicComponentDirective {
  @Input() component!: CreatePostModal;

  constructor(private viewContainerRef: ViewContainerRef, private factory: ComponentFactoryResolver) {
  }

  @HostListener('document:click')
  public onClick() {
  }
}

