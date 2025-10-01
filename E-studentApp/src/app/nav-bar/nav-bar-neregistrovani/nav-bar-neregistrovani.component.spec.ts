import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavBarNeregistrovaniComponent } from './nav-bar-neregistrovani.component';

describe('NavBarNeregistrovaniComponent', () => {
  let component: NavBarNeregistrovaniComponent;
  let fixture: ComponentFixture<NavBarNeregistrovaniComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NavBarNeregistrovaniComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NavBarNeregistrovaniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
