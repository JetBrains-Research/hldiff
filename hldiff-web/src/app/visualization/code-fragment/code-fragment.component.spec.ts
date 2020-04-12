import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CodeFragmentComponent } from './code-fragment.component';

describe('CodeFragmentComponent', () => {
  let component: CodeFragmentComponent;
  let fixture: ComponentFixture<CodeFragmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CodeFragmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CodeFragmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
