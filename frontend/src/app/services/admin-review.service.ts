import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Review } from '../models/review.model';
import { Observable } from 'rxjs';
import { AdminReviewAction } from '../models/admin-review-action.model';

@Injectable({
  providedIn: 'root'
})
export class AdminReviewService {
  private baseUrl = 'http://localhost:8080/admin/reviews';

  constructor(private http: HttpClient) {}

  getPendingReviews(): Observable<Review[]> {
    return this.http.get<Review[]>(this.baseUrl);
  }

  updateReviewStatus(reviewId: number, action: AdminReviewAction): Observable<Review> {
    return this.http.put<Review>(`${this.baseUrl}/${reviewId}`, action);
  }
}
