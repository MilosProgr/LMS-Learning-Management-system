import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UlogaEditComponent } from './uloga-edit.component';

describe('UlogaEditComponent', () => {
  let component: UlogaEditComponent;
  let fixture: ComponentFixture<UlogaEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UlogaEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UlogaEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
