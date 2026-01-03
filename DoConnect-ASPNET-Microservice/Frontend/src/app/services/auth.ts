import { Injectable, signal, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private http = inject(HttpClient);
  private router = inject(Router);
  private apiUrl = 'http://localhost:5000/api/auth';

  currentUser = signal<any>(null);
  token = signal<string | null>(localStorage.getItem('token'));

  constructor() {
    const savedToken = localStorage.getItem('token');
    if (savedToken) {
      // Decode token to set currentUser (simplified for now)
      this.currentUser.set({ username: 'User' }); 
    }
  }

  register(data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, data);
  }

  login(data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, data).pipe(
      tap((res: any) => {
        if (res.isSuccess) {
          localStorage.setItem('token', res.token);
          this.token.set(res.token);
          this.currentUser.set({ username: 'User' }); // Should decode JWT
          this.router.navigate(['/']);
        }
      })
    );
  }

  logout() {
    localStorage.removeItem('token');
    this.token.set(null);
    this.currentUser.set(null);
    this.router.navigate(['/login']);
  }

  isAuthenticated() {
    return !!this.token();
  }
}
