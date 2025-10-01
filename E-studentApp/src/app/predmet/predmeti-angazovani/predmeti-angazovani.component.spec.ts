import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PredmetiAngazovaniComponent } from './predmeti-angazovani.component';

describe('PredmetiAngazovaniComponent', () => {
  let component: PredmetiAngazovaniComponent;
  let fixture: ComponentFixture<PredmetiAngazovaniComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PredmetiAngazovaniComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PredmetiAngazovaniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
