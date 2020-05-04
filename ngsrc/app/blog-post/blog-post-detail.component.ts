import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {BlogPostService} from "./blog-post.service";
import {Observable, throwError} from "rxjs";
import {BlogPost} from "./blog-post";
import {MatSnackBar} from "@angular/material/snack-bar";
import {catchError, switchMap} from "rxjs/operators";

@Component({
    selector: 'app-blog-post-detail',
    templateUrl: './blog-post-detail.component.html',
})
export class BlogPostDetailComponent implements OnInit {

    public blogPost$: Observable<BlogPost>;

    constructor(private route: ActivatedRoute, private blogPostService: BlogPostService, private snackBar: MatSnackBar) {
    }

    /**
     * @override
     */
    public ngOnInit() {
        this.blogPost$ = this.route.params.pipe(
            switchMap(routeParams => this.blogPostService.find(routeParams.slug, 'detail')),
            catchError(error => {
                this.snackBar.open('Could not load blog post', 'OK');
                return throwError(error);
            })
        );
    }
}
