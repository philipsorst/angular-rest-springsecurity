import {CollectionResult} from "./collection-result";
import {Observable} from "rxjs";
import {Sort} from "./sort";
import {HalApiService} from "./hal-api.service";
import {Parameter} from "./parameter";
import {HalResource} from "./hal-resource";

export class RestResourceService<T>
{
    constructor(protected halApiService: HalApiService, protected endpoint: string)
    {
    }

    public save(resource: T): Observable<T>
    {
        if (resource.hasOwnProperty('_links')) {
            return this.update(resource);
        }

        return this.create(resource);
    }

    public create(resource: T): Observable<T>
    {
        return this.halApiService.post(this.getBasePath(), resource);
    }

    public update(resource: T | HalResource): Observable<T>
    {
        return this.halApiService.put(resource);
    }

    public list(
        page: number = null,
        size: number = null,
        sort: Sort[] = null,
        projection: string = null
    ): Observable<CollectionResult<T>>
    {
        return this.halApiService.getCollectionResult<T>(
            this.getBasePath(), page, size, sort, projection
        );
    }

    public query(
        method: string,
        parameters: Parameter[],
        page: number = null,
        size: number = null,
        sort: Sort[] = null,
    ): Observable<CollectionResult<T>>
    {
        let url = this.getBasePath() + '/search/' + method;

        return this.halApiService.getCollectionResult<T>(
            url, page, size, sort, null, parameters
        );
    }

    private getBasePath()
    {
        return this.halApiService.getRestApiBase() + this.endpoint;
    }

    public find(id: any, projection: string = null): Observable<T>
    {
        return this.halApiService.getSingleResult<T>(
            this.getBasePath() + '/' + id, projection
        );
    }
}
