import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './components/AllHomePages/login-page/login-page.component';
import { AdminHomepageComponent } from './components/AllHomePages/admin-homepage/admin-homepage.component';
import { CentreAddComponent } from './components/centre-add/centre-add.component';
import {AddUserComponent} from './components/add-user/add-user.component';
import {UserDetailsComponent} from './components/user-details/user-details.component';
import {UsersListComponent} from './components/users-list/users-list.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { AddCenteradminsComponent } from './components/add-centeradmins/add-centeradmins.component';
import { CentresListComponent } from './components/centres-list/centres-list.component';
import { CentreDetailsComponent } from './components/centre-details/centre-details.component';
import { UserQuestionaryComponent } from './user-questionary/user-questionary.component';
import { ComplaintsOverviewComponent } from './components/complaints-overview/complaints-overview.component';
import { AdminRegistrationComponent } from './components/admin-registration/admin-registration.component';
import { UserLoginComponent } from './components/user-login/user-login.component';
import { InitialPasswordChangeComponent } from './components/initial-password-change/initial-password-change.component';
import { AuthGuard } from './guards/auth.guard';
import { AppointmentDetailsComponent } from './components/appointment-details/appointment-details.component';




const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: UserLoginComponent },
  { path: 'initial-password-change/:id', component: InitialPasswordChangeComponent },
  { path: 'complaints', component: ComplaintsOverviewComponent, canActivate: [AuthGuard] },
  { path: 'users', component: UsersListComponent, canActivate: [AuthGuard] },
  {path: 'adminHomePage', component: AdminHomepageComponent, canActivate: [AuthGuard] },
  { path: 'users/:id', component: UserDetailsComponent, canActivate: [AuthGuard] },
  { path: 'add', component: AddUserComponent, canActivate: [AuthGuard] },
  { path: 'profile', component: UserProfileComponent, canActivate: [AuthGuard] },
  { path: 'add', component: AddUserComponent, canActivate: [AuthGuard] },
  { path: 'admin-add', component: AdminRegistrationComponent, canActivate: [AuthGuard] },
  { path: 'centres/add', component: CentreAddComponent, canActivate: [AuthGuard]},
  { path: 'appointments/:id', component: AppointmentDetailsComponent, canActivate: [AuthGuard]},
  { path: 'addcenteradmins', component:AddCenteradminsComponent, canActivate: [AuthGuard]},
  { path: 'questionnary', component: UserQuestionaryComponent, canActivate: [AuthGuard] },
  { path: 'centres', component: CentresListComponent },
  {path: 'centres/:id', component: CentreDetailsComponent, canActivate: [AuthGuard] },

];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
