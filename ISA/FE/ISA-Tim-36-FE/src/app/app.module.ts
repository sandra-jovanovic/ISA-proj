import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './components/AllHomePages/login-page/login-page.component';
import { AddUserComponent } from './components/add-user/add-user.component';
import { UserDetailsComponent } from './components/user-details/user-details.component';
import { UsersListComponent } from './components/users-list/users-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CentreAddComponent } from './components/centre-add/centre-add.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { AddCenteradminsComponent } from './components/add-centeradmins/add-centeradmins.component';
import { CentresListComponent } from './components/centres-list/centres-list.component';
import { CentreDetailsComponent } from './components/centre-details/centre-details.component';
import { UserQuestionaryComponent } from './user-questionary/user-questionary.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import {MatNativeDateModule} from '@angular/material/core';
import { MatLegacyTableModule as MatTableModule } from '@angular/material/legacy-table';
import { MatSortModule} from '@angular/material/sort';
import { ComplaintsOverviewComponent } from './components/complaints-overview/complaints-overview.component';
import { AdminRegistrationComponent } from './components/admin-registration/admin-registration.component';
import { AdminHomepageComponent } from './components/AllHomePages/admin-homepage/admin-homepage.component';
import { CentreCalendarComponent } from './components/centre-calendar/centre-calendar.component';
import { FlatpickrModule } from 'angularx-flatpickr';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { NewExaminationComponent } from './components/new-examination/new-examination.component';
import { UserLoginComponent } from './components/user-login/user-login.component';
import { InitialPasswordChangeComponent } from './components/initial-password-change/initial-password-change.component';
import { AppointmentDetailsComponent } from './components/appointment-details/appointment-details.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    AddUserComponent,
    UserDetailsComponent,
    UsersListComponent,
    CentreAddComponent,
    UserProfileComponent,
    AddCenteradminsComponent,
    CentresListComponent,
    CentreDetailsComponent,
    UserQuestionaryComponent,
    ComplaintsOverviewComponent,
    AdminRegistrationComponent,
    AdminHomepageComponent,
    CentreCalendarComponent,
    NewExaminationComponent,
    UserLoginComponent,
    InitialPasswordChangeComponent,
    AppointmentDetailsComponent,
  ],
  imports: [
    MatNativeDateModule,
    MatTableModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NoopAnimationsModule,
    MatSortModule,
    NgbModalModule,
    FlatpickrModule.forRoot(),
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    }),
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
