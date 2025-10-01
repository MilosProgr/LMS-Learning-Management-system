import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RegistrovaniKorisniciComponent } from './registrovani-korisnici.component';

describe('RegistrovaniKorisniciComponent', () => {
  let component: RegistrovaniKorisniciComponent;
  let fixture: ComponentFixture<RegistrovaniKorisniciComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegistrovaniKorisniciComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RegistrovaniKorisniciComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
