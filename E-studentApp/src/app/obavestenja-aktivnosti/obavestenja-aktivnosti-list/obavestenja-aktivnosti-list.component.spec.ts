import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObavestenjaAktivnostiListComponent } from './obavestenja-aktivnosti-list.component';

describe('ObavestenjaAktivnostiListComponent', () => {
  let component: ObavestenjaAktivnostiListComponent;
  let fixture: ComponentFixture<ObavestenjaAktivnostiListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ObavestenjaAktivnostiListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ObavestenjaAktivnostiListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
