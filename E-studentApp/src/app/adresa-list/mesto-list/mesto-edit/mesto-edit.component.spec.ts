import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MestoEditComponent } from './mesto-edit.component';

describe('MestoEditComponent', () => {
  let component: MestoEditComponent;
  let fixture: ComponentFixture<MestoEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MestoEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MestoEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
