import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <nav class="glass-morphism navbar">
      <div class="logo" routerLink="/">
        <span class="logo-do">Do</span><span class="logo-connect">Connect</span>
      </div>
      
      <div class="nav-links">
        <a routerLink="/" routerLinkActive="active" [routerLinkActiveOptions]="{exact: true}">Feed</a>
        <a href="#">Explore</a>
        <a href="#">Notifications</a>
      </div>

      <div class="auth-box">
        <ng-container *ngIf="auth.isAuthenticated(); else noAuth">
          <span class="username">{{ auth.currentUser()?.username }}</span>
          <button class="btn-primary" (click)="auth.logout()">Logout</button>
        </ng-container>
        <ng-template #noAuth>
          <button class="btn-login" routerLink="/login">Login</button>
          <button class="btn-primary" routerLink="/register">Join Now</button>
        </ng-template>
      </div>
    </nav>
  `,
  styles: [`
    .navbar {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 0 40px;
      height: 70px;
      margin: 20px 40px;
      position: sticky;
      top: 20px;
      z-index: 1000;
    }

    .logo {
      font-size: 24px;
      font-weight: 800;
      cursor: pointer;
    }

    .logo-do { color: var(--text-main); }
    .logo-connect { color: var(--primary); }

    .nav-links {
      display: flex;
      gap: 30px;
    }

    .nav-links a {
      color: var(--text-muted);
      text-decoration: none;
      font-weight: 500;
      transition: color 0.3s;
    }

    .nav-links a:hover, .nav-links a.active {
      color: var(--primary);
    }

    .auth-box {
      display: flex;
      gap: 15px;
      align-items: center;
    }

    .btn-login {
      background: transparent;
      border: 1px solid var(--border);
      color: var(--text-main);
      padding: 8px 18px;
      border-radius: 10px;
      cursor: pointer;
    }

    .username {
      font-weight: 500;
      color: var(--text-muted);
    }
  `]
})
export class NavbarComponent {
  auth = inject(AuthService);
}
