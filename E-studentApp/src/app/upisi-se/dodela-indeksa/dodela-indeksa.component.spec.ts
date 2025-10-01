import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DodelaIndeksaComponent } from './dodela-indeksa.component';

describe('DodelaIndeksaComponent', () => {
  let component: DodelaIndeksaComponent;
  let fixture: ComponentFixture<DodelaIndeksaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DodelaIndeksaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DodelaIndeksaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
