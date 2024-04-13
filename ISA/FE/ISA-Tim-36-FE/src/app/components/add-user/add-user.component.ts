import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})


export class AddUserComponent implements OnInit {
  user:User = {
    name: '',
    surname: '',
    email: '',
    role: 'KORISNIK',
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

  constructor(private userService: UserService) { }

  ngOnInit() {
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

    this.userService.create(data)
      .subscribe({
        next: (res) => {
          console.log(res);
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
      role:'KORISNIK',
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
