import { TestBed } from '@angular/core/testing';

import { HLDiffService } from './hldiff.service';

describe('HldiffService', () => {
  let service: HLDiffService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HLDiffService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
