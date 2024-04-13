import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {

  currentUsers?: User[];
  users?: User[];
  currentUser: User = {};
  currentIndex = -1;
  name = '';
  surname = '';
  isMedicaCentreAdmin = false;

  constructor(private userService: UserService, private authService: AuthService) { }

  ngOnInit(): void {
    this.retrieveUsers();
    this.isMedicaCentreAdmin = this.authService.isMedicalCenterAdmin();
  }

  retrieveUsers(): void {
    this.userService.getAll()
      .subscribe({
        next: (data) => {
          this.users = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  refreshList(): void {
    this.retrieveUsers();
    this.currentUser = {};
    this.currentIndex = -1;
  }

  setActiveUser(user: User, index: number): void {
    this.currentUser = user;
    this.currentIndex = index;
  }

 /* removeAllUsers(): void {
    this.userService.deleteAll()
      .subscribe({
        next: (res) => {
          console.log(res);
          this.refreshList();
        },
        error: (e) => console.error(e)
      });
  }*/

  filterByNameAndSurname(): void {
    this.userService.getAll()
      .subscribe({
        next: (data) => {
          this.users = data.filter(user => user.name?.includes(this.name) && user.surname?.includes(this.surname));
        },
        error: (e) => console.error(e)
      });
  }

}
