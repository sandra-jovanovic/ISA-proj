import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-initial-password-change',
  templateUrl: './initial-password-change.component.html',
  styleUrls: ['./initial-password-change.component.css']
})
export class InitialPasswordChangeComponent {

  oldPassword = "";
  newPassword = "";
  repeatedNewPassword = "";

  error = "";

  constructor(private userService: UserService, private router: Router, private route: ActivatedRoute) {}

  changePassword() {
    this.error = "";
    if (this.newPassword !== this.repeatedNewPassword) {
      this.error = "Passwords do not match";
      return;
    }

    this.userService.changeInitialPassword(this.route.snapshot.params['id'],
      this.oldPassword, this.newPassword)
      .subscribe({
        next: data => {
          this.router.navigate(['login']);
        },
        error: err => {
          this.error = "Invalid data";
        }
      })

  }

}
