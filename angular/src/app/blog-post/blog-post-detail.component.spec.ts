import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {BlogPostDetailComponent} from './blog-post-detail.component';

describe('BlogPostDetailComponent', () => {
  let component: BlogPostDetailComponent;
  let fixture: ComponentFixture<BlogPostDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [BlogPostDetailComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BlogPostDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
