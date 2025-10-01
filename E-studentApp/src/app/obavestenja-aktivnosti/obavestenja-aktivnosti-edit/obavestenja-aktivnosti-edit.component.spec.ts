import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObavestenjaAktivnostiEditComponent } from './obavestenja-aktivnosti-edit.component';

describe('ObavestenjaAktivnostiEditComponent', () => {
  let component: ObavestenjaAktivnostiEditComponent;
  let fixture: ComponentFixture<ObavestenjaAktivnostiEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ObavestenjaAktivnostiEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ObavestenjaAktivnostiEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
