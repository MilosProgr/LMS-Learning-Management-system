import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdresaListComponent } from './adresa-list.component';

describe('AdresaListComponent', () => {
  let component: AdresaListComponent;
  let fixture: ComponentFixture<AdresaListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdresaListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdresaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
