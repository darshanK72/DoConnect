import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-post-card',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="glass-morphism post-card">
      <div class="post-header">
        <div class="avatar"></div>
        <div class="user-info">
          <div class="username-row">
            <span class="username">{{ post.username }}</span>
            <span class="time">{{ post.createdAt | date:'short' }}</span>
          </div>
          <div class="title">{{ post.title }}</div>
        </div>
      </div>

      <div class="post-body">
        <p class="content">{{ post.content }}</p>
        <div *ngIf="post.imageUrl" class="post-image-container">
          <img [src]="'http://localhost:5002' + post.imageUrl" class="post-image">
        </div>
      </div>

      <div class="post-actions">
        <button class="action-btn">
          <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M14 9l-5 5 5 5V9z"></path>
          </svg>
          Upvote
        </button>
        <button class="action-btn">
          <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M21 11.5a8.38 8.38 0 0 1-.9 3.8 8.5 8.5 0 0 1-7.6 4.7 8.38 8.38 0 0 1-3.8-.9L3 21l1.9-5.7a8.38 8.38 0 0 1-.9-3.8 8.5 8.5 0 0 1 4.7-7.6 8.38 8.38 0 0 1 3.8-.9h.5a8.48 8.48 0 0 1 8 8v.5z"></path>
          </svg>
          Comment
        </button>
        <button class="action-btn">Share</button>
      </div>
    </div>
  `,
  styles: [`
    .post-card {
      padding: 24px;
      margin-bottom: 20px;
      transition: transform 0.2s ease;
    }

    .post-header {
      display: flex;
      gap: 15px;
      margin-bottom: 20px;
    }

    .avatar {
      width: 48px;
      height: 48px;
      border-radius: 50%;
      background: linear-gradient(45deg, var(--primary), var(--secondary));
    }

    .user-info {
      display: flex;
      flex-direction: column;
    }

    .username {
      font-weight: 700;
      color: var(--text-main);
    }

    .time {
      font-size: 12px;
      color: var(--text-muted);
      margin-left: 10px;
    }

    .title {
      font-size: 14px;
      color: var(--primary);
      font-weight: 500;
    }

    .post-body .content {
      font-size: 16px;
      line-height: 1.6;
      margin-bottom: 15px;
      color: #e2e8f0;
    }

    .post-image-container {
      border-radius: 12px;
      overflow: hidden;
      border: 1px solid var(--border);
    }

    .post-image {
      width: 100%;
      display: block;
    }

    .post-actions {
      display: flex;
      gap: 20px;
      margin-top: 20px;
      border-top: 1px solid var(--border);
      padding-top: 15px;
    }

    .action-btn {
      background: transparent;
      border: none;
      color: var(--text-muted);
      display: flex;
      align-items: center;
      gap: 8px;
      cursor: pointer;
      font-weight: 500;
      transition: color 0.3s;
    }

    .action-btn:hover {
      color: var(--primary);
    }
  `]
})
export class PostCardComponent {
  @Input() post: any;
}
