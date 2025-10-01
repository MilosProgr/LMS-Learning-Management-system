import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FakultetEditComponent } from './fakultet-edit.component';

describe('FakultetEditComponent', () => {
  let component: FakultetEditComponent;
  let fixture: ComponentFixture<FakultetEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FakultetEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FakultetEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
