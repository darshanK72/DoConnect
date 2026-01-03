import { TestBed } from '@angular/core/testing';

import { Comment } from './comment';

describe('Comment', () => {
  let service: Comment;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Comment);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
