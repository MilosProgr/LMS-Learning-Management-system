import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrijavaIspitaDialogComponent } from './prijava-ispita-dialog.component';

describe('PrijavaIspitaDialogComponent', () => {
  let component: PrijavaIspitaDialogComponent;
  let fixture: ComponentFixture<PrijavaIspitaDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrijavaIspitaDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PrijavaIspitaDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
