import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {tap} from 'rxjs/operators';
import {CollectionResult} from "./collection-result";

@Injectable({
  providedIn: 'root'
})
export class RestResponseInterceptor implements HttpInterceptor
{
  /**
   * @override
   */
  public intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>
  {
    return next.handle(req).pipe(tap((event: HttpEvent<any>) => {
      if (event instanceof HttpResponse) {
        event = event.clone({body: this.modifyBody(event.body)});
      }
      return event;
    }));
  }

  private modifyBody(body: any)
  {
    console.log('body', body);
    if (body.hasOwnProperty('page')) {
      let result = new CollectionResult();
      result.pagination = {
        page: body['page'].number,
          size: body['page'].size,
        totalPages: body['page'].totalPages,
          totalElements: body['page'].totalElements
      };

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
    }

    return body;
  }
}
