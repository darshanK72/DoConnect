import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from '../../components/navbar/navbar';
import { FeedComponent } from '../../components/feed/feed';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, NavbarComponent, FeedComponent],
  template: `
    <div class="main-layout">
      <app-navbar></app-navbar>
      
      <main class="content-area">
        <div class="sidebar left-sidebar">
          <div class="glass-morphism profile-summary">
            <div class="cover"></div>
            <div class="avatar-large"></div>
            <h3>Darshan Khairnar</h3>
            <p>Software Engineer</p>
          </div>
        </div>

        <app-feed class="main-feed"></app-feed>

        <div class="sidebar right-sidebar">
          <div class="glass-morphism trending">
            <h4>Trending Topics</h4>
            <ul>
              <li>#Angular18</li>
              <li>#DotNetCore</li>
              <li>#Microservices</li>
              <li>#WebDesign</li>
            </ul>
          </div>
        </div>
      </main>
    </div>
  `,
  styles: [`
    .main-layout {
      min-height: 100vh;
    }

    .content-area {
      display: grid;
      grid-template-columns: 300px 1fr 300px;
      gap: 30px;
      padding: 0 40px;
      max-width: 1400px;
      margin: 0 auto;
    }

    .sidebar {
      position: sticky;
      top: 110px;
      height: fit-content;
    }

    .profile-summary {
      padding: 0;
      overflow: hidden;
      text-align: center;
      padding-bottom: 20px;
    }

    .cover {
      height: 80px;
      background: linear-gradient(135deg, var(--primary), var(--secondary));
    }

    .avatar-large {
      width: 80px;
      height: 80px;
      border-radius: 50%;
      background: var(--bg-card);
      margin: -40px auto 10px;
      border: 4px solid var(--bg-dark);
    }

    .trending {
      padding: 20px;
    }

    .trending h4 {
      margin-bottom: 15px;
      color: var(--primary);
    }

    .trending ul {
      list-style: none;
    }

    .trending li {
      padding: 10px 0;
      border-bottom: 1px solid var(--border);
      color: var(--text-muted);
      cursor: pointer;
    }

    .trending li:hover { color: var(--text-main); }

    @media (max-width: 1100px) {
      .right-sidebar { display: none; }
      .content-area { grid-template-columns: 250px 1fr; }
    }

    @media (max-width: 800px) {
      .left-sidebar { display: none; }
      .content-area { grid-template-columns: 1fr; }
    }
  `]
})
export class HomeComponent {}
