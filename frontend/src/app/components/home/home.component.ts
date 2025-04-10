import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterModule, FormsModule, CommonModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  displayName: string = '';
  showSearchByDisplayName = false;
  showSearchByIdForm = false;
  showSearchByZipForm = false;
  restaurantId: number | null = null;
  zipCode: string = '';
  selectedAllergy: string = '';
  searchError = '';
  errorMessage = '';

  constructor(private router: Router) {}

  goToUserProfile(): void {
    if (this.displayName.trim()) {
      this.router.navigate(['/users', this.displayName.trim()]);
    }
  }

  toggleSearchByDisplayName(): void {
    this.showSearchByDisplayName = !this.showSearchByDisplayName;
    this.showSearchByIdForm = false;
    this.showSearchByZipForm = false;
  }

  toggleSearchById(): void {
    this.showSearchByIdForm = !this.showSearchByIdForm;
    this.showSearchByDisplayName = false;
    this.showSearchByZipForm = false;
    this.errorMessage = '';
  }
  
  toggleSearchByZip(): void {
    this.showSearchByZipForm = !this.showSearchByZipForm;
    this.showSearchByDisplayName = false;
    this.showSearchByIdForm = false;
    this.searchError = '';
  }
  

  searchRestaurantById(): void {
    if (this.restaurantId !== null && this.restaurantId > 0) {
      this.router.navigate(['/restaurant', this.restaurantId]);
    } else {
      this.errorMessage = 'Veuillez entrer un ID valide.';
    }
  }

  searchByZipAndAllergy(): void {
    if (!this.zipCode.match(/^[0-9]{5}$/)) {
      this.searchError = 'Le code postal doit contenir exactement 5 chiffres.';
      return;
    }
  
    if (!this.selectedAllergy) {
      this.searchError = 'Veuillez sélectionner une allergie.';
      return;
    }
  
    this.router.navigate(['/restaurant-search'], {
      queryParams: {
        zipCode: this.zipCode,
        allergy: this.selectedAllergy
      }
    });
  }
}
