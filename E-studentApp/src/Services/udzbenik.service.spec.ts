import { TestBed } from '@angular/core/testing';

import { UdzbenikService } from './udzbenik.service';

describe('UdzbenikService', () => {
  let service: UdzbenikService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UdzbenikService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
