import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FakultetPrikazComponent } from './fakultet-prikaz.component';

describe('FakultetPrikazComponent', () => {
  let component: FakultetPrikazComponent;
  let fixture: ComponentFixture<FakultetPrikazComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FakultetPrikazComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FakultetPrikazComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
