import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IzdavanjeDokumenataComponent } from './izdavanje-dokumenata.component';

describe('IzdavanjeDokumenataComponent', () => {
  let component: IzdavanjeDokumenataComponent;
  let fixture: ComponentFixture<IzdavanjeDokumenataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IzdavanjeDokumenataComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(IzdavanjeDokumenataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
