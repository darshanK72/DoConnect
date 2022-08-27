import { TestBed } from '@angular/core/testing';

import { QuestionServiceService } from './question-service.service';

describe('QuestionServiceService', () => {
  let service: QuestionServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(QuestionServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
