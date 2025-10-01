import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IzdavanjeUdzbenikaComponent } from './izdavanje-udzbenika.component';

describe('IzdavanjeUdzbenikaComponent', () => {
  let component: IzdavanjeUdzbenikaComponent;
  let fixture: ComponentFixture<IzdavanjeUdzbenikaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IzdavanjeUdzbenikaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(IzdavanjeUdzbenikaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
