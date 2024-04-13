import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { User } from '../../models/user.model';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  
  @Input() viewMode = false;

  @Input() currentUser: User = {
    name: '',
    surname: '',
    email: '',
    points: 0,
    password:'',
    role:'',
    gender:'',
    street:'',
    city:'',
    number:'',
    country:'',
    username:'',
    phoneNumber:'',
    job:'',
    companyName:'',
    
  };
  
  message = '';
  oldPassword ?:string;
  confirmPassword = '';

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getUser(AppComponent.userId+"");
    }
  }

  getUser(id: string): void {
    this.userService.get(id)
      .subscribe({
        next: (data) => {
          this.oldPassword = data.password;
          this.currentUser = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  updatePublished(status: boolean): void {
    if(this.confirmPassword == this.currentUser.password)
    {
      const data = {
        name: this.currentUser.name,
        surname: this.currentUser.surname,
        email: this.currentUser.email,
        points: this.currentUser.points,
        
        password:this.currentUser.password,
        role:this.currentUser.role,

      };

      this.message = '';

      this.userService.update(this.currentUser.id, data)
        .subscribe({
          next: (res) => {
            console.log(res);
            this.message = res.message ? res.message : 'The status was updated successfully!';
          },
          error: (e) => console.error(e)
        });
    }
  }

  updateUser(): void {
    if(this.confirmPassword == this.currentUser.password || this.oldPassword == this.currentUser.password)
    {
      this.message = '';

      if(confirm("Are you sure to update "+this.currentUser.name)) 
        {
        this.userService.update(this.currentUser.id, this.currentUser)
          .subscribe({
            next: (res) => {
              console.log(res);
              this.message = res.message ? res.message : 'This user was updated successfully!';
            },
            error: (e) => {
              console.error(e);
              this.message = "Validation not good!";
            }
                });
      }
    }
    else
    {
      this.message = "Validation not good! Confirm password first!";
    }
  }

  deleteUser(): void 
  {  
    if(confirm("Are you sure to delete "+this.currentUser.name)) 
    {
      console.log("Implement delete functionality here");
    
      this.userService.delete(this.currentUser.id)
        .subscribe({
          next: (res) => {
            console.log(res);
            this.router.navigate(['/users']);
          },
          error: (e) => console.error(e)
        });
    } 
  }



}
