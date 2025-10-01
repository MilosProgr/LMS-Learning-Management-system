import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpisiSeComponent } from './upisi-se.component';

describe('UpisiSeComponent', () => {
  let component: UpisiSeComponent;
  let fixture: ComponentFixture<UpisiSeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpisiSeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpisiSeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
