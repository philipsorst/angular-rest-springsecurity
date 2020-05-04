import {Component, OnInit} from '@angular/core';
import {BlogPostService} from "./blog-post.service";
import {BlogPost} from "./blog-post";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Observable, throwError} from "rxjs";
import {catchError, map} from "rxjs/operators";

@Component({
    selector: 'app-blog-post-list',
    templateUrl: './blog-post-list.component.html'
})
export class BlogPostListComponent implements OnInit {
    public blogPosts$: Observable<BlogPost[]>;

    constructor(private blogPostService: BlogPostService, private snackBar: MatSnackBar)
    {
    }

    /**
     * @override
     */
    public ngOnInit() {
        this.blogPosts$ = this.blogPostService.list(0, null, [{property: 'created', direction: 'DESC'}])
            .pipe(
                catchError(error => {
                    this.snackBar.open('Could not load blog posts', 'OK');
                    return throwError(error);
                }),
                map(result => result.entries),
            );
    }
}
