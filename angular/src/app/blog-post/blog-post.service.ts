import {Injectable} from '@angular/core';
import {BlogPost} from "./blog-post";
import {RestResourceService} from "../rest/rest-resource.service";
import {HalApiService} from "../rest/hal-api.service";

@Injectable({
    providedIn: 'root'
})
export class BlogPostService extends RestResourceService<BlogPost>
{
    constructor(protected halApiService: HalApiService)
    {
        super(halApiService, 'blog-posts');
    }
}
