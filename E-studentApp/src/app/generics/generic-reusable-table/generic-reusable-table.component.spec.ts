import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenericReusableTableComponent } from './generic-reusable-table.component';

describe('GenericReusableTableComponent', () => {
  let component: GenericReusableTableComponent;
  let fixture: ComponentFixture<GenericReusableTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GenericReusableTableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GenericReusableTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
