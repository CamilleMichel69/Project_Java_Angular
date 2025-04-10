import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RestaurantService } from '../../services/restaurant.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-restaurant-detail',
  standalone: true,
  templateUrl: './restaurant-profile.component.html',
  styleUrls: ['./restaurant-profile.component.css'],
  imports: [CommonModule]
})
export class RestaurantProfileComponent implements OnInit {
  restaurant: any = null;
  errorMessage = '';

  constructor(
    private route: ActivatedRoute,
    private restaurantService: RestaurantService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.restaurantService.getRestaurantById(id).subscribe({
      next: (res) => {
        this.restaurant = res;
      },
      error: (err) => {
        this.errorMessage = 'Aucun restaurant trouvé avec cet ID.';
      }
    });
  }

  navigateToHome() {
    this.router.navigate(['/']);
  }
}
