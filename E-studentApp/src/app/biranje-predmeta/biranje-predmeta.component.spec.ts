import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BiranjePredmetaComponent } from './biranje-predmeta.component';

describe('BiranjePredmetaComponent', () => {
  let component: BiranjePredmetaComponent;
  let fixture: ComponentFixture<BiranjePredmetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BiranjePredmetaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BiranjePredmetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
