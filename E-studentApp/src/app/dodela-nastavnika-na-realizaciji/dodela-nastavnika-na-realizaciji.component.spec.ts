import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DodelaNastavnikaNaRealizacijiComponent } from './dodela-nastavnika-na-realizaciji.component';

describe('DodelaNastavnikaNaRealizacijiComponent', () => {
  let component: DodelaNastavnikaNaRealizacijiComponent;
  let fixture: ComponentFixture<DodelaNastavnikaNaRealizacijiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DodelaNastavnikaNaRealizacijiComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DodelaNastavnikaNaRealizacijiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
