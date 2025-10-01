import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KreiranjeIspitniRokComponent } from './kreiranje-ispitni-rok.component';

describe('KreiranjeIspitniRokComponent', () => {
  let component: KreiranjeIspitniRokComponent;
  let fixture: ComponentFixture<KreiranjeIspitniRokComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [KreiranjeIspitniRokComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(KreiranjeIspitniRokComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
