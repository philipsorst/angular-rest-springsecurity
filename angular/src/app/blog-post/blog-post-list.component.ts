import {Component, OnInit} from '@angular/core';
import {BlogPostService} from "./blog-post.service";
import {BlogPost} from "./blog-post";
import {MatSnackBar} from "@angular/material";
import {CollectionResult} from "../rest/collection-result";

@Component({
    selector: 'app-blog-post-list',
    templateUrl: './blog-post-list.component.html',
    styles: []
})
export class BlogPostListComponent implements OnInit
{
    public loading: boolean = false;

    public blogPosts: BlogPost[];

    constructor(private blogPostService: BlogPostService, private snackbar: MatSnackBar)
    {
    }

    /**
     * @override
     */
    public ngOnInit()
    {
        this.loading = true;
        this.blogPostService.list(0, null, [{property: 'created', direction: 'DESC'}]).subscribe(
            (result: CollectionResult<BlogPost>) => {
                this.blogPosts = result.entries;
            },
            (error) => {
                this.snackbar.open('Could not load blog posts', 'OK');
            },
            () => {
                this.loading = false;
            }
        );
    }
}
