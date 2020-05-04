import {HttpClient, HttpParams} from "@angular/common/http";
import {Inject, Injectable, InjectionToken} from "@angular/core";
import {Sort} from "./sort";
import {CollectionResult} from "./collection-result";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Parameter} from "./parameter";

export const REST_API_BASE = new InjectionToken<string>('rest-api-base');

@Injectable({
    providedIn: 'root'
})
export class HalApiService
{
    constructor(private httpClient: HttpClient, @Inject(REST_API_BASE) private restApiBase: string)
    {
    }

    public getHttpClient(): HttpClient
    {
        return this.httpClient;
    }

    public getRestApiBase(): string
    {
        return this.restApiBase;
    }

    public getCollectionResult<T>(
        url: string,
        page: number = null,
        size: number = null,
        sort: Sort[] = null,
        projection: string = null,
        additionalParameters: Parameter[] = null
    ): Observable<CollectionResult<T>>
    {
        let params = new HttpParams();
        if (null != additionalParameters) {
            additionalParameters.forEach((parameter: Parameter) => {
                params = params.append(parameter.key, parameter.value);
            });
        }

        if (null != page) {
            params = params.append('page', String(page));
        }

        if (null != size) {
            params = params.append('size', String(size));
        }

        if (null != projection) {
            params = params.append('projection', projection);
        }

        if (null != sort) {
            sort.forEach((sort: Sort) => {
                params = params.append('sort', sort.property + ',' + sort.direction)
            });
        }

        return this.getHttpClient().get(url, {params: params}).pipe(map((body) => {
            let result = new CollectionResult<T>();
            if (body.hasOwnProperty('page')) {
                result.pagination = {
                    page: body['page'].number,
                    size: body['page'].size,
                    totalPages: body['page'].totalPages,
                    totalElements: body['page'].totalElements
                };
            }

            let propertyNames = Object.getOwnPropertyNames(body['_embedded']);
            if (propertyNames.length !== 1) {
                throw new Error('Unexpected _embedded content');
            }

            let entries = [];
            body['_embedded'][propertyNames[0]].forEach((entry: any) => {
                entries.push(entry);
            });
            result.entries = entries;

            return result;
        }))
    }

    public getSingleResult<T>(url: string, projection: string = null): Observable<T>
    {
        let params = new HttpParams();
        if (null != projection) {
            params = params.append('projection', projection);
        }
        return this.getHttpClient().get<T>(url, {params: params});
    }
}
