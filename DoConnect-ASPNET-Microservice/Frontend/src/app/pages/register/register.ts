import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  template: `
    <div class="auth-container">
      <div class="glass-morphism auth-card">
        <div class="auth-header">
          <h2>Create Account</h2>
          <p>Make the most of your professional life.</p>
        </div>

        <form (submit)="onRegister()" #registerForm="ngForm">
          <div class="name-row">
            <div class="form-group">
              <label>First Name</label>
              <input type="text" [(ngModel)]="firstName" name="firstName" class="input-field" required>
            </div>
            <div class="form-group">
              <label>Last Name</label>
              <input type="text" [(ngModel)]="lastName" name="lastName" class="input-field" required>
            </div>
          </div>

          <div class="form-group">
            <label>Username</label>
            <input type="text" [(ngModel)]="username" name="username" class="input-field" required>
          </div>

          <div class="form-group">
            <label>Email</label>
            <input type="email" [(ngModel)]="email" name="email" class="input-field" required>
          </div>
          
          <div class="form-group">
            <label>Password</label>
            <input type="password" [(ngModel)]="password" name="password" class="input-field" required placeholder="6+ characters">
          </div>

          <button type="submit" class="btn-primary auth-btn" [disabled]="!registerForm.valid">
            Agree & Join
          </button>
        </form>

        <div class="auth-footer">
          <p>Already on DoConnect? <a routerLink="/login">Sign in</a></p>
        </div>
      </div>
    </div>
  `,
  styles: [`
    /* Reusing styles from Login with some additions */
    .auth-container {
      min-height: 100vh;
      display: flex;
      justify-content: center;
      align-items: center;
      background: radial-gradient(circle at top left, var(--secondary), transparent),
                  radial-gradient(circle at bottom right, var(--primary), transparent);
      padding: 40px 20px;
    }

    .auth-card {
      width: 100%;
      max-width: 500px;
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

    .name-row {
      display: grid;
      grid-template-columns: 1fr 1fr;
      gap: 15px;
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
export class RegisterComponent {
  auth = inject(AuthService);
  firstName = '';
  lastName = '';
  username = '';
  email = '';
  password = '';

  onRegister() {
    const data = {
      firstName: this.firstName,
      lastName: this.lastName,
      username: this.username,
      email: this.email,
      password: this.password
    };
    this.auth.register(data).subscribe(() => {
      // Navigate to login
    });
  }
}
