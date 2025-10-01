import { TestBed } from '@angular/core/testing';

import { IzdavanjeUdzbenikService } from './izdavanje-udzbenika-service.service';

describe('IzdavanjeUdzbenikaServiceService', () => {
  let service: IzdavanjeUdzbenikService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IzdavanjeUdzbenikService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
