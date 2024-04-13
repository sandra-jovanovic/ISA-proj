import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-admin-registration',
  templateUrl: './admin-registration.component.html',
  styleUrls: ['./admin-registration.component.css']
})
export class AdminRegistrationComponent implements OnInit {

    user: User = {
      name: '',
      surname: '',
      email: '',
      role: 'ADMINISTRATOR_SISTEMA',
      points:0,
      password:'',
      username:'',
      gender:'',
      phoneNumber:'',
      jmbg:'',
      job:'',
      companyName:'',
      street:'',
      city:'',
      country:'',
      number:''
    };

    submitted = false;

    constructor(private userService: UserService, private authService: AuthService, private router: Router) { }

    ngOnInit() {
      //if (!this.authService.isAdmin()) {
       // this.router.navigate(["/login"]);
      //}
    }


    saveUser(): void {
      const data = {
        name: this.user.name,
        surname: this.user.surname,
        email: this.user.email,
        role: this.user.role,
        points: 0,
        password:this.user.password,
        username:this.user.username,
        gender:this.user.gender,
        phoneNumber:this.user.phoneNumber,
        jmbg:this.user.jmbg,
        job:this.user.job,
        companyName:this.user.companyName,
        street:this.user.street,
        city:this.user.city,
        country:this.user.country,
        number:this.user.number
      };

      this.userService.createAdmin(data)
        .subscribe({
          next: (res) => {
            this.submitted = true;
          },
          error: (e) => console.error(e)
        });
    }

    newUser(): void {
      this.submitted = false;
      this.user = {
        name: '',
        surname: '',
        email:'',
        role:'ADMINISTRATOR_SISTEMA',
        points:0,
        password:'',
        username:'',
        gender:'',
        phoneNumber:'',
        jmbg:'',
        job:'',
        companyName:'',
        street:'',
        city:'',
        country:'',
        number:''
      };
    }

}
