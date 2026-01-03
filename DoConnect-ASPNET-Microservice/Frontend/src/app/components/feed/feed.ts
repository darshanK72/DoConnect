import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PostService } from '../../services/post';
import { PostCardComponent } from '../post-card/post-card';
import { CreatePostComponent } from '../create-post/create-post';

@Component({
  selector: 'app-feed',
  standalone: true,
  imports: [CommonModule, PostCardComponent, CreatePostComponent],
  template: `
    <div class="feed-container">
      <app-create-post></app-create-post>
      
      <div class="posts-list">
        <app-post-card *ngFor="let post of posts()" [post]="post"></app-post-card>
        
        <div *ngIf="posts().length === 0" class="empty-state glass-morphism">
          <p>No posts yet. Be the first to start a conversation!</p>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .feed-container {
      max-width: 700px;
      margin: 0 auto;
      padding: 20px;
    }

    .empty-state {
      padding: 40px;
      text-align: center;
      color: var(--text-muted);
    }
  `]
})
export class FeedComponent implements OnInit {
  private postService = inject(PostService);
  posts = signal<any[]>([]);

  ngOnInit() {
    this.loadPosts();
  }

  loadPosts() {
    this.postService.getPosts().subscribe(data => {
      this.posts.set(data);
    });
  }
}
