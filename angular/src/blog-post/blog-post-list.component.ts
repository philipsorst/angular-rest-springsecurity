import {Component, OnInit} from '@angular/core';
import {BlogPostService} from "./blog-post.service";
import {BlogPost} from "./blog-post";
import {MatSnackBar} from "@angular/material";

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
        this.blogPostService.getAll({sort: [{path: 'created', order: 'DESC'}]}).subscribe(
            (blogPosts: BlogPost[]) => {
                this.blogPosts = blogPosts;
            },
            (error) => {
                this.snackbar.open('Could not load blog psots', 'OK');
            },
            () => {
                this.loading = false;
            }
        );
    }
}
