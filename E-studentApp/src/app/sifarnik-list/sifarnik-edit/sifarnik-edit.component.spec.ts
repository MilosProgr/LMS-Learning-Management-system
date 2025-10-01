import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SifarnikEditComponent } from './sifarnik-edit.component';

describe('SifarnikEditComponent', () => {
  let component: SifarnikEditComponent;
  let fixture: ComponentFixture<SifarnikEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SifarnikEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SifarnikEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
