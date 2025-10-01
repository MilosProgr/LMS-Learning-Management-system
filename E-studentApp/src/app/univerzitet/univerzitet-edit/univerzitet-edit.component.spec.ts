import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UniverzitetEditComponent } from './univerzitet-edit.component';

describe('UniverzitetEditComponent', () => {
  let component: UniverzitetEditComponent;
  let fixture: ComponentFixture<UniverzitetEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UniverzitetEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UniverzitetEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
