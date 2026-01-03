import { TestBed } from '@angular/core/testing';

import { AnswerServiceService } from './answer-service.service';

describe('AnswerServiceService', () => {
  let service: AnswerServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AnswerServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
