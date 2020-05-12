import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {catchError, map, switchMap} from "rxjs/operators";
import {BehaviorSubject, Observable, throwError} from "rxjs";
import {BlogPost} from "./blog-post";
import {BlogPostService} from "./blog-post.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({templateUrl: './blog-post-edit.component.html'})
export class BlogPostEditComponent implements OnInit
{
    public blogPost$: Observable<BlogPost>

    public submitting = false;

    constructor(
        private route: ActivatedRoute,
        private blogPostService: BlogPostService,
        private snackBar: MatSnackBar,
        private router: Router)
    {
    }

    /**
     * @override
     */
    public ngOnInit(): void
    {
        this.blogPost$ = this.route.params.pipe(
            map(params => params.slug),
            switchMap(slug => {
                if (null == slug) {
                    return new BehaviorSubject(new BlogPost());
                } else {
                    return this.loadBlogPost(slug);
                }
            })
        );
    }

    private loadBlogPost(slug: string): Observable<BlogPost>
    {
        return this.blogPostService.find(slug, 'edit').pipe(
            catchError(error => {
                this.snackBar.open('Could not load blog post', 'OK');
                return throwError(error);
            })
        );
    }

    public save(blogPost: BlogPost)
    {
        this.submitting = true;
        this.blogPostService.save(blogPost)
            .pipe(
                catchError(error => {
                    this.snackBar.open('Saving blog post failed', 'OK');
                    return throwError(error);
                })
            )
            .subscribe(blogPost => {
                    this.router.navigate(['/blog-posts', blogPost.slug]);
                }
            )
            .add(() => this.submitting = false);
    }
}
