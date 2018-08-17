import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {NotFoundComponent} from "../not-found/not-found.component";
import {BlogPostListComponent} from "../blog-post/blog-post-list.component";
import {BlogPostDetailComponent} from "../blog-post/blog-post-detail.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: '/blog-posts',
    pathMatch: 'full'
  },
  {
    path: 'blog-posts',
    children: [
      {
        path: '',
        component: BlogPostListComponent
      },
      {
        path: ':id',
        component: BlogPostDetailComponent
      }
    ]
  },
  {
    path: '**',
    component: NotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule
{
}
