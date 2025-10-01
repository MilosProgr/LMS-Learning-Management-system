import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdresaEditComponent } from './adresa-edit.component';

describe('AdresaEditComponent', () => {
  let component: AdresaEditComponent;
  let fixture: ComponentFixture<AdresaEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdresaEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdresaEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
