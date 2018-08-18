import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {BlogPostService} from "./blog-post.service";
import {Subscription} from "rxjs";
import {MatSnackBar} from "@angular/material";
import {BlogPost} from "./blog-post";

@Component({
    selector: 'app-blog-post-detail',
    templateUrl: './blog-post-detail.component.html',
})
export class BlogPostDetailComponent implements OnInit, OnDestroy
{
    public loading: boolean = false;

    public blogPost: BlogPost;

    private routeParamsSubscription: Subscription;

    constructor(private route: ActivatedRoute, private blogPostService: BlogPostService, private snackBar: MatSnackBar)
    {
    }

    /**
     * @override
     */
    public ngOnInit()
    {
        this.loading = true;
        this.routeParamsSubscription = this.route.params.subscribe((params) => {
            this.loading = true;
            this.blogPostService.get(params.slug).subscribe(
                (blogPost: BlogPost) => {
                    this.blogPost = blogPost;
                },
                (error) => {
                    this.snackBar.open('Could not load blog post', 'OK');
                },
                () => {
                    this.loading = false;
                }
            );
        })
    }

    /**
     * @override
     */
    public ngOnDestroy()
    {
        this.routeParamsSubscription.unsubscribe();
    }
}
