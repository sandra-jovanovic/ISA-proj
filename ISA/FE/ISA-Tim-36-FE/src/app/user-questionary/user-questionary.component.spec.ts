import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserQuestionaryComponent } from './user-questionary.component';

describe('UserQuestionaryComponent', () => {
  let component: UserQuestionaryComponent;
  let fixture: ComponentFixture<UserQuestionaryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserQuestionaryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserQuestionaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
