import {Component, OnInit} from '@angular/core';
import {BlogPostService} from "./blog-post.service";
import {BlogPost} from "./blog-post";

@Component({
  selector: 'app-blog-post-list',
  templateUrl: './blog-post-list.component.html',
  styles: []
})
export class BlogPostListComponent implements OnInit
{
  constructor(private blogPostService: BlogPostService)
  {
  }

  /**
   * @override
   */
  public ngOnInit()
  {
    this.blogPostService.page(1).subscribe((blogPosts: BlogPost[]) => {
      console.log('blogPosts', blogPosts);
    })
  }
}
