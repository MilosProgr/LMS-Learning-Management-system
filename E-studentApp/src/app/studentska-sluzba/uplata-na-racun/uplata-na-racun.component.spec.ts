import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UplataNaRacunComponent } from './uplata-na-racun.component';

describe('UplataNaRacunComponent', () => {
  let component: UplataNaRacunComponent;
  let fixture: ComponentFixture<UplataNaRacunComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UplataNaRacunComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UplataNaRacunComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
