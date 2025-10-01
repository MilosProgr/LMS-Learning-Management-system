import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpisOcenaComponent } from './upis-ocena.component';

describe('UpisOcenaComponent', () => {
  let component: UpisOcenaComponent;
  let fixture: ComponentFixture<UpisOcenaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpisOcenaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpisOcenaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
