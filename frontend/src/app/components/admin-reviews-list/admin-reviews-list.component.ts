import { Component, OnInit } from '@angular/core';
import { AdminReviewService } from '../../services/admin-review.service';
import { AdminReviewAction } from '../../models/admin-review-action.model';
import { Review } from '../../models/review.model';
import { NgFor, NgIf } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-reviews-list',
  imports: [NgIf, NgFor],
  templateUrl: './admin-reviews-list.component.html',
  styleUrl: './admin-reviews-list.component.css'
})
export class AdminReviewsListComponent implements OnInit {
  pendingReviews: Review[] = [];

  constructor(private adminReviewService: AdminReviewService, private router: Router) {}

  ngOnInit(): void {
    this.loadPendingReviews();
  }

  loadPendingReviews(): void {
    this.adminReviewService.getPendingReviews().subscribe((reviews) => {
      this.pendingReviews = reviews;
    });
  }

  approve(id: number | undefined): void {
    if (id === undefined) return;
  
    const action: AdminReviewAction = { accept: true };
    this.adminReviewService.updateReviewStatus(id, action).subscribe(() => {
      this.loadPendingReviews();
    });
  }
  
  reject(id: number | undefined): void {
    if (id === undefined) return;
  
    const action: AdminReviewAction = { accept: false };
    this.adminReviewService.updateReviewStatus(id, action).subscribe(() => {
      this.loadPendingReviews();
    });
  }

  navigateToHome() {
    this.router.navigate(['/']);
  }
}
