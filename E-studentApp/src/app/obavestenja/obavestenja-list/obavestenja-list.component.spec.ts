import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObavestenjaListComponent } from './obavestenja-list.component';

describe('ObavestenjaListComponent', () => {
  let component: ObavestenjaListComponent;
  let fixture: ComponentFixture<ObavestenjaListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ObavestenjaListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ObavestenjaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
