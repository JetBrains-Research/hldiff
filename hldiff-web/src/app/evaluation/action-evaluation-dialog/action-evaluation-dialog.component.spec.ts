import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActionEvaluationDialogComponent } from './action-evaluation-dialog.component';

describe('ActionEvaluationDialogComponent', () => {
  let component: ActionEvaluationDialogComponent;
  let fixture: ComponentFixture<ActionEvaluationDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActionEvaluationDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActionEvaluationDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
