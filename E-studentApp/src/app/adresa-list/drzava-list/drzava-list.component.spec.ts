import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DrzavaListComponent } from './drzava-list.component';

describe('DrzavaListComponent', () => {
  let component: DrzavaListComponent;
  let fixture: ComponentFixture<DrzavaListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DrzavaListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DrzavaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
