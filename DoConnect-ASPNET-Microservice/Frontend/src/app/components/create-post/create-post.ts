import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PostService } from '../../services/post';

@Component({
  selector: 'app-create-post',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="glass-morphism create-post-card">
      <div class="header">
        <div class="avatar"></div>
        <textarea 
          placeholder="What's on your mind?" 
          [(ngModel)]="content"
          class="content-input"
        ></textarea>
      </div>
      
      <div *ngIf="imagePreview" class="preview-container">
        <img [src]="imagePreview" class="image-preview">
        <button class="remove-btn" (click)="removeImage()">×</button>
      </div>

      <div class="footer">
        <label class="upload-btn">
          <input type="file" (change)="onFileSelected($event)" accept="image/*" hidden>
          <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <rect x="3" y="3" width="18" height="18" rx="2" ry="2"></rect>
            <circle cx="8.5" cy="8.5" r="1.5"></circle>
            <polyline points="21 15 16 10 5 21"></polyline>
          </svg>
          Photo
        </label>
        
        <button class="btn-primary" [disabled]="!content && !selectedFile" (click)="submitPost()">
          Post Content
        </button>
      </div>
    </div>
  `,
  styles: [`
    .create-post-card {
      padding: 20px;
      margin-bottom: 30px;
    }

    .header {
      display: flex;
      gap: 15px;
    }

    .avatar {
      width: 48px;
      height: 48px;
      border-radius: 50%;
      background: var(--border);
      flex-shrink: 0;
    }

    .content-input {
      width: 100%;
      background: transparent;
      border: none;
      color: var(--text-main);
      font-size: 18px;
      resize: none;
      min-height: 80px;
      outline: none;
    }

    .preview-container {
      position: relative;
      margin-top: 15px;
      border-radius: 12px;
      overflow: hidden;
      max-height: 400px;
    }

    .image-preview {
      width: 100%;
      display: block;
    }

    .remove-btn {
      position: absolute;
      top: 10px;
      right: 10px;
      background: rgba(0,0,0,0.5);
      color: white;
      border: none;
      width: 30px;
      height: 30px;
      border-radius: 50%;
      cursor: pointer;
      font-size: 20px;
    }

    .footer {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-top: 20px;
      border-top: 1px solid var(--border);
      padding-top: 15px;
    }

    .upload-btn {
      display: flex;
      align-items: center;
      gap: 8px;
      color: var(--text-muted);
      cursor: pointer;
      transition: color 0.3s;
    }

    .upload-btn:hover { color: var(--primary); }

    .btn-primary:disabled {
      opacity: 0.5;
      cursor: not-allowed;
    }
  `]
})
export class CreatePostComponent {
  private postService = inject(PostService);
  
  content = '';
  selectedFile: File | null = null;
  imagePreview: string | null = null;

  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.selectedFile = file;
      const reader = new FileReader();
      reader.onload = () => this.imagePreview = reader.result as string;
      reader.readAsDataURL(file);
    }
  }

  removeImage() {
    this.selectedFile = null;
    this.imagePreview = null;
  }

  submitPost() {
    const formData = new FormData();
    formData.append('title', 'New Post'); // For now
    formData.append('content', this.content);
    if (this.selectedFile) {
      formData.append('image', this.selectedFile);
    }

    this.postService.createPost(formData).subscribe(() => {
      this.content = '';
      this.removeImage();
      // Emit event to refresh feed
    });
  }
}
