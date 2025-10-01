import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IspitniRokEditComponent } from './ispitni-rok-edit.component';

describe('IspitniRokEditComponent', () => {
  let component: IspitniRokEditComponent;
  let fixture: ComponentFixture<IspitniRokEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IspitniRokEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(IspitniRokEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
