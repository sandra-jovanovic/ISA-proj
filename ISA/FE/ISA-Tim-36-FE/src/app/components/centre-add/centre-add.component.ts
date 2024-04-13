import { Time } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { CentresService } from 'src/app/services/centres.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-centre-add',
  templateUrl: './centre-add.component.html',
  styleUrls: ['./centre-add.component.css']
})
export class CentreAddComponent implements OnInit {

  centreAdmins: User[] = [];

  name: string = '';
  street: string = '';
  number: string = '';
  description: string = '';
  administrator: number = 0;
  averageRate: number = 0;
  reserveAppointment: string='';
  noAdmins = true;
  errorMessage = "";

  constructor(private userService: UserService, private centresService: CentresService, private router: Router) { }

  ngOnInit(): void {
    this.userService.getAllCentreAdmins().subscribe(data => {
      this.centreAdmins = data;
      this.noAdmins = data.length === 0;
    }, err => {
      this.noAdmins = true;
    });
  }

  /*
      ///OVO SAM TI ZAKOMENTARISAO
      //nisam skontao sta si radila posto na submit ne izbaci objekat centra sa atributima koji trebaju da budu


  saveCentre() {
    this.errorMessage = "";

    if (!this.name || !this.street || !this.number  || !this.description || this.averageRate === 0 || this.administrator === 0) {
      this.errorMessage = "Data invalid";
      return;

    }

    this.centresService.create({
      name: this.name,
      address: this.street,
      number: this.number,
      description: this.description,
      averageRate: this.averageRate,
      adminId: this.administrator,


    }).subscribe(data => {
      this.router.navigate(["/centres"]);
    }, err => {
      console.log(err)
      this.errorMessage = err.message;
    })
  }
*/



saveCentre(): void {
  const data = {
    name: this.name,
      address: this.street,
      number: this.number,
      description: this.description,
      averageRate: this.averageRate,
      adminId: this.administrator
  };

  this.centresService.create(data)
    .subscribe({
      next: (res) => {
        console.log(res);
        this.router.navigate(["/centres"]); ///DODATO ZA NAVIGACIJU DO CENTARA
      },
      error: (e) => console.error(e)
    });
}




}
