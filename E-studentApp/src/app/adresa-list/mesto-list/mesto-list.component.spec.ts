import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MestoListComponent } from './mesto-list.component';

describe('MestoListComponent', () => {
  let component: MestoListComponent;
  let fixture: ComponentFixture<MestoListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MestoListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MestoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
