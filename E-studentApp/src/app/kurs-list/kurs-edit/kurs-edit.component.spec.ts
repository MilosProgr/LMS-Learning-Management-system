import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KursEditComponent } from './kurs-edit.component';

describe('KursEditComponent', () => {
  let component: KursEditComponent;
  let fixture: ComponentFixture<KursEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [KursEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(KursEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
