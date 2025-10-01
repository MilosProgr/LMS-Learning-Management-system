import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PredmetEditComponent } from './predmet-edit.component';

describe('PredmetEditComponent', () => {
  let component: PredmetEditComponent;
  let fixture: ComponentFixture<PredmetEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PredmetEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PredmetEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
