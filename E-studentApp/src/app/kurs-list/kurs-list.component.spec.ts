import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KursListComponent } from './kurs-list.component';

describe('KursListComponent', () => {
  let component: KursListComponent;
  let fixture: ComponentFixture<KursListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [KursListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(KursListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
