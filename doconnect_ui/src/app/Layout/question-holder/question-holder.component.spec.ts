import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionHolderComponent } from './question-holder.component';

describe('QuestionHolderComponent', () => {
  let component: QuestionHolderComponent;
  let fixture: ComponentFixture<QuestionHolderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QuestionHolderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QuestionHolderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
