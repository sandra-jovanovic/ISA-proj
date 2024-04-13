import { Component, Input, OnInit } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})

export class UserDetailsComponent implements OnInit {

  
  @Input() viewMode = false;

  @Input() currentUser: User = {
    name: '',
    surname: '',
    email: '',
    points: 0,
    password: '',
  };
  
  message = '';

  shownPasswod :boolean = false;


  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getUser(this.route.snapshot.params["id"]);
    }
  }

  getUser(id: string): void {
    this.userService.get(id)
      .subscribe({
        next: (data) => {
          this.currentUser = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  updatePublished(status: boolean): void {
    const data = {
      name: this.currentUser.name,
      surname: this.currentUser.surname,
      email: this.currentUser.email,
      points: this.currentUser.points,
      password: this.currentUser.password,
      
      
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

  updateUser(): void {
    this.message = '';

    if(confirm("Are you sure to update "+this.currentUser.name)) 
      {
      this.userService.update(this.currentUser.id, this.currentUser)
        .subscribe({
          next: (res) => {
            console.log(res);
            this.message = res.message ? res.message : 'This tutorial was updated successfully!';
          },
          error: (e) => {
            console.error(e);
            this.message = "Validation not good!";
          }
              });
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
