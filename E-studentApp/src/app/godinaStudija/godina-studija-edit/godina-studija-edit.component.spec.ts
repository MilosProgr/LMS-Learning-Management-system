import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GodinaStudijaEditComponent } from './godina-studija-edit.component';

describe('GodinaStudijaEditComponent', () => {
  let component: GodinaStudijaEditComponent;
  let fixture: ComponentFixture<GodinaStudijaEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GodinaStudijaEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GodinaStudijaEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
