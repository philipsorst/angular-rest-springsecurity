import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {NotFoundComponent} from './not-found/not-found.component';
import {BlogPostListComponent} from './blog-post/blog-post-list.component';
import {BlogPostDetailComponent} from './blog-post/blog-post-detail.component';
import {HttpClientModule} from "@angular/common/http";
import {SidenavToggleComponent} from "./sidenav/sidenav-toggle.component";
import {REST_API_BASE} from "./rest/hal-api.service";
import {UserListComponent} from "./user/user-list.component";
import {UserDetailComponent} from "./user/user-detail.component";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatListModule} from "@angular/material/list";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatCardModule} from "@angular/material/card";
import {MatIconModule} from "@angular/material/icon";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatButtonModule} from "@angular/material/button";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {BlogPostEditComponent} from "./blog-post/blog-post-edit.component";
import {MatMenuModule} from "@angular/material/menu";
import {FormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatFormFieldModule} from "@angular/material/form-field";

@NgModule({
    declarations: [
        AppComponent,
        NotFoundComponent,
        BlogPostListComponent,
        BlogPostDetailComponent,
        BlogPostEditComponent,
        SidenavToggleComponent,
        UserListComponent,
        UserDetailComponent
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule,
        FormsModule,
        BrowserAnimationsModule,
        MatSidenavModule,
        MatListModule,
        MatInputModule,
        MatFormFieldModule,
        MatToolbarModule,
        MatCardModule,
        MatIconModule,
        MatProgressSpinnerModule,
        MatButtonModule,
        MatSnackBarModule,
        MatMenuModule
    ],
    providers: [
        {provide: REST_API_BASE, useValue: 'http://localhost:8080/api/'}
    ],
    bootstrap: [AppComponent]
})
export class AppModule
{
}
