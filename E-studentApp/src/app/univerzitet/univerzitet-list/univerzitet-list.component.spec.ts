import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UniverzitetListComponent } from './univerzitet-list.component';

describe('UniverzitetListComponent', () => {
  let component: UniverzitetListComponent;
  let fixture: ComponentFixture<UniverzitetListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UniverzitetListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UniverzitetListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
