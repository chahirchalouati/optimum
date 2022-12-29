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
    totalElements: number;
    totalPages: number;
    empty: boolean;

  }

  export abstract class Selectable {
    selected: boolean = false;
  }

  export interface PageRequest {
    page?: number,
    size?: number,
    sort?: string[]
  }

  export type Direction = "DESC" | "ASC";

  export class Sorted {
    private readonly field: string;
    private direction: Direction;

    private constructor(field: string, direction: Pageable.Direction) {
      this.field = field;
      this.direction = direction;
    }

    public static with(field: string, direction: Direction) {
      return new Sorted(field, direction).param();
    }

    private param(): Array<string> {
      return [`sort=${this.field}`, `${this.field}dir=${this.direction.toLowerCase()}`];
    }
  }
}

export class Sorted {
}
