import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObavestenjeEditComponent } from './obavestenja-edit.component';

describe('ObavestenjaEditComponent', () => {
  let component: ObavestenjeEditComponent;
  let fixture: ComponentFixture<ObavestenjeEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ObavestenjeEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ObavestenjeEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
