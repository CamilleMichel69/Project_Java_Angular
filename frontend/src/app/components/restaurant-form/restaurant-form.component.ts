import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RestaurantService } from '../../services/restaurant.service';
import { Router } from '@angular/router';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-create-restaurant',
  standalone: true,
  templateUrl: './restaurant-form.component.html',
  styleUrls: ['./restaurant-form.component.css'],
  imports: [NgIf, ReactiveFormsModule],
})
export class CreateRestaurantComponent {
  restaurantForm: FormGroup;
  successMessage = '';
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private restaurantService: RestaurantService,
    private router: Router
  ) {
    this.restaurantForm = this.fb.group({
      name: ['', Validators.required],
      address: ['', Validators.required],
      city: ['', Validators.required],
      zipCode: ['', [Validators.required, Validators.pattern(/^\d{5}$/)]]
    });
  }

  onSubmit(): void {
    if (this.restaurantForm.invalid) return;

    this.restaurantService.createRestaurant(this.restaurantForm.value).subscribe({
      next: (res) => {
        this.successMessage = 'Restaurant créé avec succès !';
        this.errorMessage = '';
        this.restaurantForm.reset();
      },
      error: (err) => {
        if (typeof err.error === 'string') {
          this.errorMessage = err.error;
        } else if (err.error?.message) {
          this.errorMessage = err.error.message;
        } else {
          this.errorMessage = 'Erreur lors de la création.';
        }
        this.successMessage = '';
      }       
    });
  }

  navigateToHome() {
    this.router.navigate(['/']);
  }
}
