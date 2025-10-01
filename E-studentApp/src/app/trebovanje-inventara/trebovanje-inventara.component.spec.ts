import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrebovanjeInventaraComponent } from './trebovanje-inventara.component';

describe('TrebovanjeInventaraComponent', () => {
  let component: TrebovanjeInventaraComponent;
  let fixture: ComponentFixture<TrebovanjeInventaraComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TrebovanjeInventaraComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TrebovanjeInventaraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
