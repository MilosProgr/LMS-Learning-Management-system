import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SemestarListComponent } from './semestar-list.component';

describe('SemestarListComponent', () => {
  let component: SemestarListComponent;
  let fixture: ComponentFixture<SemestarListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SemestarListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SemestarListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
