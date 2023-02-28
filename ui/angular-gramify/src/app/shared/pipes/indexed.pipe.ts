import {Pipe, PipeTransform} from '@angular/core';
import {Indexable} from "../domain/Post";

@Pipe({
  name: 'index'
})
export class IndexedPipe implements PipeTransform {

  transform<T extends Indexable>(items: T[], start: number = 0): T[] {
    items.forEach(item => item.index = start++);
    return items;

  }

}
