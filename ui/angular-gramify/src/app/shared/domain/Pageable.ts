import { StringUtils} from "../../utils/ObjectsUtils";
import isBlank = StringUtils.isBlank;

export type Direction = "DESC" | "ASC";

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
  page?: number;
  size?: number;
  sort?: string[];
}

export class Sortable {
  private readonly _field: string;
  private readonly direction: Direction;

  private constructor(field: string, direction: Direction) {
    this._field = field;
    this.direction = direction;
  }

  public static with(field: string, direction: Direction) {
    if (!isBlank(field) && !!direction)
      return new Sortable(field, direction).param();
    throw new Error();
  }

  private param(): string {
    return `${this._field},${this.direction.toLowerCase()}`;
  }
}

