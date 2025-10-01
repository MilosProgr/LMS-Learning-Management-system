import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TipEvaluacijeEditComponent } from './tip-evaluacije-edit.component';

describe('TipEvaluacijeEditComponent', () => {
  let component: TipEvaluacijeEditComponent;
  let fixture: ComponentFixture<TipEvaluacijeEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TipEvaluacijeEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TipEvaluacijeEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
