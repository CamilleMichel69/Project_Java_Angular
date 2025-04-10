import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { UserFormComponent } from './components/user-form/user-form.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { CreateRestaurantComponent } from './components/restaurant-form/restaurant-form.component';
import { RestaurantListComponent } from './components/restaurant-list/restaurant-list.component';
import { RestaurantProfileComponent } from './components/restaurant-profile/restaurant-profile.component';
import { RestaurantSearchComponent } from './components/restaurant-search/restaurant-search.component';
import { ReviewFormComponent } from './components/review-form/review-form.component';
import { ReviewsListComponent } from './components/reviews-list/reviews-list.component';
import { AdminReviewsListComponent } from './components/admin-reviews-list/admin-reviews-list.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'create-user', component: UserFormComponent },
  { path: 'users', component: UserListComponent },
  { path: 'users/:displayName', component: UserProfileComponent },
  { path: 'create-restaurant', component: CreateRestaurantComponent },
  { path: 'restaurants', component: RestaurantListComponent },
  { path: 'restaurant/:id', component: RestaurantProfileComponent },
  { path: 'restaurant-search', component: RestaurantSearchComponent},
  { path: 'create-review', component: ReviewFormComponent},
  { path: 'reviews', component: ReviewsListComponent},
  { path: 'admin/reviews', component: AdminReviewsListComponent}
];
