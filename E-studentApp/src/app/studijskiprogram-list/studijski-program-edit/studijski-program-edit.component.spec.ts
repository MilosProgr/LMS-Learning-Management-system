import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudijskiProgramEditComponent } from './studijski-program-edit.component';

describe('StudijskiProgramEditComponent', () => {
  let component: StudijskiProgramEditComponent;
  let fixture: ComponentFixture<StudijskiProgramEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudijskiProgramEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(StudijskiProgramEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
