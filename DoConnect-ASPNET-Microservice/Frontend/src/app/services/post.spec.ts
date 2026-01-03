import { TestBed } from '@angular/core/testing';

import { Post } from './post';

describe('Post', () => {
  let service: Post;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Post);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
