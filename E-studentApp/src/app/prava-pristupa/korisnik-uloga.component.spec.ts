import { ComponentFixture, TestBed } from '@angular/core/testing';
import { KorisnikUlogaComponent } from './korisnik-uloga.component';

describe('KorisnikUlogaComponent', () => {
  let component: KorisnikUlogaComponent;
  let fixture: ComponentFixture<KorisnikUlogaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [KorisnikUlogaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(KorisnikUlogaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
