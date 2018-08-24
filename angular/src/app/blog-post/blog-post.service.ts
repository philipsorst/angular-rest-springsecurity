import {Injectable, Injector} from '@angular/core';
import {BlogPost} from "./blog-post";
import {RestService} from "hal-4-angular";

@Injectable({
  providedIn: 'root'
})
export class BlogPostService extends RestService<BlogPost>
{
  constructor(injector: Injector)
  {
    super(BlogPost, 'blog-posts', injector);
  }
}
