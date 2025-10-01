import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrovaniKorisniciEditComponent } from './registrovani-korisnici-edit.component';

describe('RegistrovaniKorisniciEditComponent', () => {
  let component: RegistrovaniKorisniciEditComponent;
  let fixture: ComponentFixture<RegistrovaniKorisniciEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegistrovaniKorisniciEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RegistrovaniKorisniciEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
