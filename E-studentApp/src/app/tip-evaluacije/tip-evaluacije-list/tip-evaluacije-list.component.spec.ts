import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TipEvaluacijeListComponent } from './tip-evaluacije-list.component';

describe('TipEvaluacijeListComponent', () => {
  let component: TipEvaluacijeListComponent;
  let fixture: ComponentFixture<TipEvaluacijeListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TipEvaluacijeListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TipEvaluacijeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
