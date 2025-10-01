import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudijskiprogramComponent } from './studijskiprogram.component';

describe('StudijskiprogramComponent', () => {
  let component: StudijskiprogramComponent;
  let fixture: ComponentFixture<StudijskiprogramComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudijskiprogramComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(StudijskiprogramComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
