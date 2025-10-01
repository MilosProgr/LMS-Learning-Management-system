import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZahtevZaGodisnjiOdmorComponent } from './zahtev-za-godisnji-odmor.component';

describe('ZahtevZaGodisnjiOdmorComponent', () => {
  let component: ZahtevZaGodisnjiOdmorComponent;
  let fixture: ComponentFixture<ZahtevZaGodisnjiOdmorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ZahtevZaGodisnjiOdmorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ZahtevZaGodisnjiOdmorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
