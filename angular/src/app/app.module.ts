import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {NotFoundComponent} from '../not-found/not-found.component';
import {BlogPostListComponent} from '../blog-post/blog-post-list.component';
import {BlogPostDetailComponent} from '../blog-post/blog-post-detail.component';
import {MatListModule, MatSidenavModule, MatToolbarModule} from "@angular/material";
import {HttpClientModule} from "@angular/common/http";
import {ExternalConfigurationService} from "../rest/external-configuration.service";
import {AngularHalModule} from "hal-4-angular";

@NgModule({
  declarations: [
    AppComponent,
    NotFoundComponent,
    BlogPostListComponent,
    BlogPostDetailComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    AngularHalModule.forRoot(),
    BrowserAnimationsModule,
    MatSidenavModule,
    MatListModule,
    MatToolbarModule,
  ],
  providers: [
    {provide: 'ExternalConfigurationService', useClass: ExternalConfigurationService},
  ],
  bootstrap: [AppComponent]
})
export class AppModule
{
}
