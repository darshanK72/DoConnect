import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private http = inject(HttpClient);
  private auth = inject(AuthService);
  private apiUrl = 'http://localhost:5000/api/post';

  getPosts(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  createPost(formData: FormData): Observable<any> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.auth.token()}`
    });
    return this.http.post(this.apiUrl, formData, { headers });
  }
}
