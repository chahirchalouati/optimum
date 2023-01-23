import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'iconSize'
})
export class IconSizePipe implements PipeTransform {

  transform(value: string, size: number): string {
    return value.replace('height="20" width="20"', `height="${size}" width="${size}"`);
  }

}
