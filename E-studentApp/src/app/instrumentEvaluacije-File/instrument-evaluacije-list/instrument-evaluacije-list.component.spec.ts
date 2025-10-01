import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstrumentEvaluacijeListComponent } from './instrument-evaluacije-list.component';

describe('InstrumentEvaluacijeListComponent', () => {
  let component: InstrumentEvaluacijeListComponent;
  let fixture: ComponentFixture<InstrumentEvaluacijeListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InstrumentEvaluacijeListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InstrumentEvaluacijeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
