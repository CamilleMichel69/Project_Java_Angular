import { Component } from '@angular/core';
import { ReviewService } from '../../services/review.service';
import { Review } from '../../models/review.model';
import { NgClass, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-review-form',
  imports: [NgIf, NgClass, FormsModule],
  templateUrl: './review-form.component.html',
  styleUrl: './review-form.component.css'
})
export class ReviewFormComponent {
  review: Review = {
    submittedBy: '',
    restaurantId: 1,
    review: '',
    peanutScore: undefined,
    eggScore: undefined,
    dairyScore: undefined,
    status: 'PENDING'
  };

  message = '';
  messageType: 'success' | 'error' | '' = '';

  constructor(private reviewService: ReviewService, private router: Router) {}

  submitReview(): void {
    this.reviewService.createReview(this.review).subscribe({
      next: () => {
        this.message = 'Avis envoyé avec succès ! Dans l\'attente de validation par un administrateur.';
        this.messageType = 'success';
        this.review = {
          submittedBy: '',
          restaurantId: 1,
          review: '',
          peanutScore: undefined,
          eggScore: undefined,
          dairyScore: undefined,
          status: 'PENDING'
        };
      },
      error: (err) => {
        this.message = err.error.message;
        this.messageType = 'error';
      }
    });
  }

  navigateToHome() {
    this.router.navigate(['/']);
  }
}
