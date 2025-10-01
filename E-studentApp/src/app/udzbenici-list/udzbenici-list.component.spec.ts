import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UdzbeniciListComponent } from './udzbenici-list.component';

describe('UdzbeniciListComponent', () => {
  let component: UdzbeniciListComponent;
  let fixture: ComponentFixture<UdzbeniciListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UdzbeniciListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UdzbeniciListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
