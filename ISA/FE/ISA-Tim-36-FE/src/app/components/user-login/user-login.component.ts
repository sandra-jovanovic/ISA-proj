import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
import {AppComponent} from "../../app.component";

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent {

  username = "";
  password = "";

  error = "";

  constructor(private userService: UserService, private router: Router, private authService: AuthService) {}

  login() {
    this.userService.login(this.username, this.password)
    .subscribe({
      next: data => {

        if (data.role != null && data.id != null) {
          AppComponent.role = data.role;
          AppComponent.userId = data.id;
          console.log("Ulogovan:"+AppComponent.role);
        }

        if (!data.changedInitialPassword && (data.role === 'ADMINISTRATOR_SISTEMA' || data.role === 'ADMINISTRATOR_CENTRA_I_MEDICINSKO_OSOBLJE')) {
          this.router.navigate(['/initial-password-change/' + data.id]);
        } else {
          this.authService.login();
          this.router.navigate(['/centres']);
        }
      },
      error: err => {
        this.error = "Bad credentials"
      }
    })
  }

}
