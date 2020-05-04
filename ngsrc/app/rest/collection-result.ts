import {Pagination} from "./pagination";

export class CollectionResult<T>
{
  pagination: Pagination;
  entries: Array<T>;
}
