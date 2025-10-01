import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObavestenjaPrikazComponent } from './obavestenja-prikaz.component';

describe('ObavestenjaComponent', () => {
  let component: ObavestenjaPrikazComponent;
  let fixture: ComponentFixture<ObavestenjaPrikazComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ObavestenjaPrikazComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ObavestenjaPrikazComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
