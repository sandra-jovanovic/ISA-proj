import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-add-centeradmins',
  templateUrl: './add-centeradmins.component.html',
  styleUrls: ['./add-centeradmins.component.css']
})


export class AddCenteradminsComponent implements OnInit {

  user:User = {
    name: '',
    surname: '',
    email: '',
    password: '',
    role:'ADMINISTRATOR_CENTRA_I_MEDICINSKO_OSOBLJE',
    

  };

  submitted = false;

  constructor(private userService: UserService) { }

  ngOnInit()  {
  }

  saveUser(): void {
    const data = {
      name: this.user.name,
      surname: this.user.surname,
      email: this.user.email,
      password:this.user.password,
      role:this.user.role,
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
      password:'',
      role:'ADMINISTRATOR_CENTRA_I_MEDICINSKO_OSOBLJE',
    };
  }

}
