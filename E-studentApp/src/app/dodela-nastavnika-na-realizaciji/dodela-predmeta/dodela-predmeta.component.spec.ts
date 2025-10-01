import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DodelaPredmetaRealizacijaComponent } from './dodela-predmeta.component';

describe('DodelaPredmetaComponent', () => {
  let component: DodelaPredmetaRealizacijaComponent;
  let fixture: ComponentFixture<DodelaPredmetaRealizacijaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DodelaPredmetaRealizacijaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DodelaPredmetaRealizacijaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
