import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RestaurantService } from '../../services/restaurant.service';
import { Restaurant } from '../../models/restaurant.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-restaurant-search',
  standalone: true,
  templateUrl: './restaurant-search.component.html',
  styleUrls: ['./restaurant-search.component.css'],
  imports: [CommonModule]
})
export class RestaurantSearchComponent implements OnInit {
  restaurants: Restaurant[] = [];
  errorMessage = '';
  allergy = '';
  zipCode = '';

  constructor(
    private route: ActivatedRoute,
    private restaurantService: RestaurantService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.zipCode = params['zipCode'];
      this.allergy = params['allergy'];

      if (this.zipCode && this.allergy) {
        this.restaurantService.searchRestaurants(this.zipCode, this.allergy)
          .subscribe({
            next: (res) => {
              this.restaurants = res;
            },
            error: () => {
              this.errorMessage = "Erreur lors de la récupération des restaurants.";
            }
          });
      }
    });
  }

  get allergyLabel(): string {
    switch (this.allergy) {
      case 'peanut':
        return 'arachides';
      case 'egg':
        return 'œufs';
      case 'dairy':
        return 'produits laitiers';
      default:
        return this.allergy;
    }
  }  

  navigateToHome() {
    this.router.navigate(['/']);
  }
}
