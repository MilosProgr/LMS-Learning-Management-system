import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SemestarEditComponent } from './semestar-edit.component';

describe('SemestarEditComponent', () => {
  let component: SemestarEditComponent;
  let fixture: ComponentFixture<SemestarEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SemestarEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SemestarEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
