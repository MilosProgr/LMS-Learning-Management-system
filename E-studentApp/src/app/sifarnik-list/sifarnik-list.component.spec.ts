import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SifarnikListComponent } from './sifarnik-list.component';

describe('SifarnikListComponent', () => {
  let component: SifarnikListComponent;
  let fixture: ComponentFixture<SifarnikListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SifarnikListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SifarnikListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
