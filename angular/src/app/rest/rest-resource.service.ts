import {CollectionResult} from "./collection-result";
import {Observable} from "rxjs";
import {Sort} from "./sort";
import {HalApiService} from "./hal-api.service";

export class RestResourceService<T>
{
    constructor(protected halApiService: HalApiService, protected endpoint: string)
    {
    }

    public list(
        page: number = null,
        size: number = null,
        sort: Sort[] = null,
        projection: string = null
    ): Observable<CollectionResult<T>>
    {
        return this.halApiService.getCollectionResult<T>(
            this.halApiService.getRestApiBase() + this.endpoint, page, size, sort, projection
        );
    }

    public find(id: any, projection: string = null): Observable<T>
    {
        return this.halApiService.getSingleResult<T>(
            this.halApiService.getRestApiBase() + this.endpoint + '/' + id, projection
        );
    }
}
