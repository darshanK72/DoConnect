import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  template: `
    <div class="auth-container">
      <div class="glass-morphism auth-card">
        <div class="auth-header">
          <h2>Welcome Back</h2>
          <p>Login to stay connected with your professional world.</p>
        </div>

        <form (submit)="onLogin()" #loginForm="ngForm">
          <div class="form-group">
            <label>Email</label>
            <input type="email" [(ngModel)]="email" name="email" class="input-field" required placeholder="name@example.com">
          </div>
          
          <div class="form-group">
            <label>Password</label>
            <input type="password" [(ngModel)]="password" name="password" class="input-field" required placeholder="••••••••">
          </div>

          <button type="submit" class="btn-primary auth-btn" [disabled]="!loginForm.valid">
            Sign In
          </button>
        </form>

        <div class="auth-footer">
          <p>New to DoConnect? <a routerLink="/register">Join now</a></p>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .auth-container {
      height: 100vh;
      display: flex;
      justify-content: center;
      align-items: center;
      background: radial-gradient(circle at top right, var(--secondary), transparent),
                  radial-gradient(circle at bottom left, var(--primary), transparent);
    }

    .auth-card {
      width: 100%;
      max-width: 450px;
      padding: 40px;
    }

    .auth-header {
      text-align: center;
      margin-bottom: 30px;
    }

    .auth-header h2 {
      font-size: 32px;
      color: var(--text-main);
      margin-bottom: 10px;
    }

    .auth-header p {
      color: var(--text-muted);
    }

    .form-group {
      margin-bottom: 20px;
    }

    .form-group label {
      display: block;
      margin-bottom: 8px;
      font-weight: 500;
      color: var(--text-main);
    }

    .auth-btn {
      width: 100%;
      margin-top: 10px;
      height: 50px;
      font-size: 16px;
    }

    .auth-footer {
      text-align: center;
      margin-top: 30px;
      color: var(--text-muted);
    }

    .auth-footer a {
      color: var(--primary);
      text-decoration: none;
      font-weight: 600;
    }
  `]
})
export class LoginComponent {
  auth = inject(AuthService);
  email = '';
  password = '';

  onLogin() {
    this.auth.login({ email: this.email, password: this.password }).subscribe();
  }
}
