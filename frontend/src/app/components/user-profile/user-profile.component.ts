import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../../models/user.model';
import { UserService } from '../../services/user.service';
import { NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-user-profile',
  standalone: true,
  imports: [NgIf, FormsModule],
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  user: User | undefined;
  displayName: string | null = null;
  errorMessage: string | null = null;
  isEditing: boolean = false; 

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.displayName = this.route.snapshot.paramMap.get('displayName');

    if (this.displayName) {
      this.userService.getUserByDisplayName(this.displayName).subscribe({
        next: (data) => {
          this.user = data;
        },
        error: (err) => {
          this.errorMessage = 'Utilisateur non trouvé';
        },
      });
    }
  }

  enableEdit() {
    this.isEditing = true;
  }

  updateUser() {
    if (this.user) {
      this.userService.updateUser(this.displayName!, this.user).subscribe({
        next: (updatedUser) => {
          this.user = updatedUser;
          this.isEditing = false; 
          this.router.navigate([`/users/${this.displayName}`]); 
        },
        error: (err) => {
          this.errorMessage = 'Erreur lors de la mise à jour';
        }
      });
    }
  }

  navigateToHome() {
    this.router.navigate(['/']);
  }
}
