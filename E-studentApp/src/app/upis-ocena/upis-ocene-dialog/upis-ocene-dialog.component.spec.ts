import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpisOceneDialogComponent } from './upis-ocene-dialog.component';

describe('UpisOceneDialogComponent', () => {
  let component: UpisOceneDialogComponent;
  let fixture: ComponentFixture<UpisOceneDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpisOceneDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpisOceneDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
