// reviews-list.component.ts
import { Component, OnInit } from '@angular/core';
import { ReviewService } from '../../services/review.service';
import { Review } from '../../models/review.model';
import { NgFor, NgIf } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reviews-list',
  imports: [NgFor, NgIf],
  templateUrl: './reviews-list.component.html',
  styleUrl: './reviews-list.component.css'
})
export class ReviewsListComponent implements OnInit {
  reviews: Review[] = [];

  constructor(private reviewService: ReviewService, private router: Router) {}

  ngOnInit(): void {
    this.reviewService.getAllReviews().subscribe((data) => {
      this.reviews = data;
    });
  }

  getTranslatedStatus(status: string | undefined): string {
    switch (status) {
      case 'PENDING':
        return 'En attente d\'approbation';
      case 'ACCEPTED':
        return 'Avis accepté';
      case 'REJECTED':
        return 'Avis rejeté';
      default:
        return 'Statut inconnu';
    }
  }  

  navigateToHome() {
    this.router.navigate(['/']);
  }
}
