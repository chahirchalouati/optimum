import {Directive, ElementRef, HostListener, Input} from '@angular/core';

@Directive({
  selector: '[fallBack]'
})
export class ImgFallbackDirective {
  @Input() fallBackSrc!: string;

  constructor(private ref: ElementRef) {
  }

  @HostListener('error')
  loadFallBackSrc() {
    const element: HTMLImageElement = this.ref.nativeElement as HTMLImageElement;
    element.src = !!this.fallBackSrc ? this.fallBackSrc : 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQoNaLFFSdD4YhW8mqgDBSWY8nHnte6ANHQWz6Lsl37yA&s';
  }

}
