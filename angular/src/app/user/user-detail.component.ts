import {Component, OnDestroy, OnInit} from "@angular/core";
import {BlogPost} from "../blog-post/blog-post";
import {Subscription} from "rxjs";
import {ActivatedRoute} from "@angular/router";
import {BlogPostService} from "../blog-post/blog-post.service";
import {MatSnackBar} from "@angular/material";
import {UserService} from "./user.service";
import {User} from "./user";
import {CollectionResult} from "../rest/collection-result";

@Component({
    templateUrl: './user-detail.component.html'
})
export class UserDetailComponent implements OnInit, OnDestroy
{
    public loading: boolean = false;

    public blogPostsLoading: boolean = false;

    public user: User;

    public blogPosts: Array<BlogPost>;

    private routeParamsSubscription: Subscription;

    constructor(
        private route: ActivatedRoute,
        private userService: UserService,
        private blogPostService: BlogPostService,
        private snackBar: MatSnackBar
    )
    {
    }

    /**
     * @override
     */
    public ngOnInit()
    {
        this.loading = true;
        this.blogPostsLoading = true;
        this.routeParamsSubscription = this.route.params.subscribe((params) => {
            this.loading = true;
            this.userService.find(params.username, 'detail').subscribe(
                (user: User) => {
                    this.user = user;
                },
                (error) => {
                    this.snackBar.open('Could not load author', 'OK');
                },
                () => {
                    this.loading = false;
                }
            );

            this.blogPostsLoading = true;
            this.blogPostService.query(
                'author',
                [{key: 'name', value: params.username}],
                0,
                null,
                [{property: 'created', direction: 'DESC'}]
            ).subscribe(
                (blogPosts: CollectionResult<BlogPost>) => {
                    this.blogPosts = blogPosts.entries;
                },
                (error) => {
                    this.snackBar.open('Could not load blog posts', 'OK');
                },
                () => {
                    this.blogPostsLoading = false;
                }
            )
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
