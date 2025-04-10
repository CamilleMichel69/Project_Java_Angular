import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private baseUrl = 'http://localhost:8080/users';

  constructor(private http: HttpClient) {}

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.baseUrl);
  }

  createUser(user: User): Observable<User> {
    return this.http.post<User>(this.baseUrl, user);
  }

  getUserByDisplayName(displayName: string): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/${displayName}`);
  }

  updateUser(displayName: string, user: User): Observable<User> {
    return this.http.put<User>(`${this.baseUrl}/${displayName}`, user);
  }
}
