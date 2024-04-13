import { Component, OnInit } from '@angular/core';
import {  Centre } from 'src/app/models/centre.model';

@Component({
  selector: 'app-centers-list',
  templateUrl: './centers-list.component.html',
  styleUrls: ['./centers-list.component.css']
})
export class CentersListComponent implements OnInit {

  centers?: Centre[];   //MORA OVAJ UPITNIK SUGAVI nzm sto

  constructor() { }

  ngOnInit(): void {
  }

}
