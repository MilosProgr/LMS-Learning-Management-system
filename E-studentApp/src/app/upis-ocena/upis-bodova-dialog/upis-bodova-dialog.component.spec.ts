import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpisBodovaDialogComponent } from './upis-bodova-dialog.component';

describe('UpisBodovaDialogComponent', () => {
  let component: UpisBodovaDialogComponent;
  let fixture: ComponentFixture<UpisBodovaDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpisBodovaDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpisBodovaDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
