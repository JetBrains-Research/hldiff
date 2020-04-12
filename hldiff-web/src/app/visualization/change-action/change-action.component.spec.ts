import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeActionComponent } from './change-action.component';

describe('ChangeActionComponent', () => {
  let component: ChangeActionComponent;
  let fixture: ComponentFixture<ChangeActionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChangeActionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeActionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
