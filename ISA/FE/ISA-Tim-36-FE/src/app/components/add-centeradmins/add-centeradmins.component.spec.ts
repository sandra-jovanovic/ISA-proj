import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCenteradminsComponent } from './add-centeradmins.component';



describe('AddCenteradminsComponent', () => {
  let component: AddCenteradminsComponent;
  let fixture: ComponentFixture<AddCenteradminsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddCenteradminsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddCenteradminsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
