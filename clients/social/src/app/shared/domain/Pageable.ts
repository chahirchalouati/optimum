export namespace Pageable {
  export interface Sort {
    empty: boolean;
    sorted: boolean;
    unsorted: boolean;
  }

  export interface Pageable {
    sort: Sort;
    pagingState: string;
    offset: number;
    pageNumber: number;
    pageSize: number;
    unpaged: boolean;
    paged: boolean;
  }

  export interface Page<T extends Selectable> {
    content: T[];
    pageable: Pageable;
    size: number;
    number: number;
    sort: Sort;
    first: boolean;
    last: boolean;
    numberOfElements: number;
    empty: boolean;

  }

  export abstract class Selectable {
    selected: boolean = false;
  }

  export interface PageRequest {
    page?: number,
    size?: number,
    sort?: string[] | string
  }
}
