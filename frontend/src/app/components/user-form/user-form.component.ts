import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user.model';
import { NgIf } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-form',
  standalone: true,
  imports: [NgIf, ReactiveFormsModule],
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css'],
})
export class UserFormComponent {
  userForm: FormGroup;
  successMessage = '';
  errorMessage = '';

  constructor(
    private fb: FormBuilder, 
    private userService: UserService, 
    private router: Router) {
    this.userForm = this.fb.group({
      displayName: ['', Validators.required],
      city: ['', Validators.required],
      zipCode: ['', [Validators.required, Validators.pattern(/^\d{5}$/)]],    
      isPeanutAllergy: [false],
      isEggAllergy: [false],
      isDairyAllergy: [false],
    });
  }

  onSubmit(): void {
    this.successMessage = '';
    this.errorMessage = '';

    if (this.userForm.valid) {
      const newUser: User = this.userForm.value;
      this.userService.createUser(newUser).subscribe({
        next: (user) => {
          this.successMessage = `Utilisateur ${user.displayName} créé avec succès !`;
          this.userForm.reset(); 
        },
        error: (error) => {
          this.errorMessage = error.error?.message || 'Une erreur est survenue.';
        }
      });
    }
  }

  navigateToHome() {
    this.router.navigate(['/']);
  }
}
