import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RasporediDogadjajaComponent } from './rasporedi-dogadjaja.component';

describe('RasporediDogadjajaComponent', () => {
  let component: RasporediDogadjajaComponent;
  let fixture: ComponentFixture<RasporediDogadjajaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RasporediDogadjajaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RasporediDogadjajaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
