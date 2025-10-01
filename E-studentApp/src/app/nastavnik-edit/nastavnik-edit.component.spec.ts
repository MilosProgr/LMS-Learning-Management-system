import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NastavnikEditComponent } from './nastavnik-edit.component';

describe('NastavnikEditComponent', () => {
  let component: NastavnikEditComponent;
  let fixture: ComponentFixture<NastavnikEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NastavnikEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NastavnikEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
