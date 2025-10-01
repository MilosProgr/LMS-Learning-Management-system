import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavBarGenericComponent } from './nav-bar-generic.component';

describe('NavBarGenericComponent', () => {
  let component: NavBarGenericComponent;
  let fixture: ComponentFixture<NavBarGenericComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NavBarGenericComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NavBarGenericComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
