import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminReviewsListComponent } from './admin-reviews-list.component';

describe('AdminReviewsListComponent', () => {
  let component: AdminReviewsListComponent;
  let fixture: ComponentFixture<AdminReviewsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminReviewsListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminReviewsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
