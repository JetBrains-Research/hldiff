import { TestBed } from '@angular/core/testing';

import { HldiffService } from './hldiff.service';

describe('HldiffService', () => {
  let service: HldiffService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HldiffService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
