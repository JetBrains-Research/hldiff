import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DiffEvaluationComponent } from './diff-evaluation.component';

describe('DiffEvaluationComponent', () => {
  let component: DiffEvaluationComponent;
  let fixture: ComponentFixture<DiffEvaluationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DiffEvaluationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DiffEvaluationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
