import {Component, OnInit} from "@angular/core";
import {BlogPost} from "../blog-post/blog-post";
import {Observable, throwError} from "rxjs";
import {ActivatedRoute} from "@angular/router";
import {BlogPostService} from "../blog-post/blog-post.service";
import {UserService} from "./user.service";
import {User} from "./user";
import {MatSnackBar} from "@angular/material/snack-bar";
import {catchError, map, switchMap} from "rxjs/operators";

@Component({
    templateUrl: './user-detail.component.html'
})
export class UserDetailComponent implements OnInit {

    public user$: Observable<User>;

    public blogPosts$: Observable<BlogPost[]>;

    constructor(
        private route: ActivatedRoute,
        private userService: UserService,
        private blogPostService: BlogPostService,
        private snackBar: MatSnackBar
    ) {
    }

    /**
     * @override
     */
    public ngOnInit() {
        this.user$ = this.route.params.pipe(
            map(params => params.username),
            switchMap(username => this.userService.find(username, 'detail')),
            catchError(error => {
                this.snackBar.open('Could not load author', 'OK');
                return throwError(error);
            })
        );

        this.blogPosts$ = this.route.params.pipe(
            map(params => params.username),
            switchMap(username => this.blogPostService.query(
                'author',
                [{key: 'name', value: username}],
                0,
                null,
                [{property: 'created', direction: 'DESC'}]
            )),
            catchError(error => {
                this.snackBar.open('Could not load blog posts', 'OK');
                return throwError(error);
            }),
            map(result => result.entries)
        );
    }
}
