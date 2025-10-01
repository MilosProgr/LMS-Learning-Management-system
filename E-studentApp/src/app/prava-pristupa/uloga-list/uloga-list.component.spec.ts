import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UlogaListComponent } from './uloga-list.component';

describe('UlogaListComponent', () => {
  let component: UlogaListComponent;
  let fixture: ComponentFixture<UlogaListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UlogaListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UlogaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
