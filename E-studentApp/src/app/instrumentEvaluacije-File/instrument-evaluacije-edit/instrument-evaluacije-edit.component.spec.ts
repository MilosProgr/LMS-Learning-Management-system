import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstrumentEvaluacijeEditComponent } from './instrument-evaluacije-edit.component';

describe('InstrumentEvaluacijeEditComponent', () => {
  let component: InstrumentEvaluacijeEditComponent;
  let fixture: ComponentFixture<InstrumentEvaluacijeEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InstrumentEvaluacijeEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InstrumentEvaluacijeEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
