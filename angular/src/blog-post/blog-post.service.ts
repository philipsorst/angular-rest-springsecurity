import {Injectable, Injector} from '@angular/core';
import {BlogPost} from "./blog-post";
import {RestService} from "angular4-hal";

@Injectable({
  providedIn: 'root'
})
export class BlogPostService extends RestService<BlogPost>
{
  constructor(injector: Injector)
  {
    super(BlogPost, 'blog_posts', injector);
  }
}
