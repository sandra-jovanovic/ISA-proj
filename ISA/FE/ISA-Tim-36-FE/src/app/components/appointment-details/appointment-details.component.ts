import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import BloodTypes from 'src/app/models/bloodTypes.model';
import BloodTypesWithQuantity from 'src/app/models/bloodTypesWithQuantity.model';
import Equipment from 'src/app/models/equipment.models';
import EquipmentWithQuantity from 'src/app/models/equipmentWithQuantity.model';
import { AppointmentService } from 'src/app/services/appointment.service';
import { BloodTypesService } from 'src/app/services/bloodTypes.services';
import { EquipmentService } from 'src/app/services/equipment.service';
import {AppComponent} from "../../app.component";

@Component({
  selector: 'app-appointment-details',
  templateUrl: './appointment-details.component.html',
  styleUrls: ['./appointment-details.component.css']
})
export class AppointmentDetailsComponent implements OnInit {

  appointmentStarted: boolean = false;
  didNotShow: boolean = false;
  conditionsNotFulfilled: boolean = false;
  nameAndSurnameUser? = '';
  nameAndSurnameMedicalWorker? = '';
  dateAndTime?: Date;
  details = "";

  selectedQuantity: number = 0;
  selectedQuantity2: number = 0;

  selectedEquipment?: Equipment;
  selectedBloodType?: BloodTypes;

  centreId: number = 0;

  equipment: Equipment[] = [];
  bloodTypes : BloodTypes[] = [];

  selectedEquipments: EquipmentWithQuantity[] = [];
  selectedBloodTypes: BloodTypesWithQuantity[] =[];

  constructor(private appointmentService: AppointmentService, private router: Router, private activatedRoute: ActivatedRoute,
    private equipmentService: EquipmentService, private bloodTypesService:BloodTypesService) {}

  startAppointement() {
    this.appointmentStarted = true;
  }

  isAdmin(): boolean
  {
    return AppComponent.role === 'ADMINISTRATOR_SISTEMA';
  }
  isMedicalAdmin(): boolean
  {
    return AppComponent.role === 'ADMINISTRATOR_CENTRA_I_MEDICINSKO_OSOBLJE';
  }
  ngOnInit() {
    this.appointmentService.getById(this.activatedRoute.snapshot.params['id'])
    .subscribe({
      next: data => {
        this.nameAndSurnameMedicalWorker = data.medicalWorker?.name + " " + data.medicalWorker?.surname;
        this.nameAndSurnameUser = data.user?.name ? data.user?.name + " " + data.user?.surname : "";
        this.dateAndTime = data.dateTime;
        this.centreId = data.centre.id;
      }
    })

    this.equipmentService.getAvailableEquipment()
    .subscribe({
      next: data => {
        this.equipment = data;
      },
      error: err => {
        console.log(err);
      }
    })

    this.bloodTypesService.getAvailableBloodTypes()
    .subscribe({
      next: data => {
        this.bloodTypes = data;
      },
      error: err => {
        console.log(err);
      }
    })

  }

  markEquipmentAsUsed() {
    if (!this.selectedEquipment?.stockQuantity) {
      alert("Please select an equipment");
      return;
    }

    if (this.selectedQuantity === 0 || this.selectedQuantity <=0 ) {
      alert("You have to select positive number");
      return;
    }

    if (this.selectedQuantity > this.selectedEquipment!.stockQuantity) {
      alert("More selected than available");
      return;
    }

    const existingEquipment = this.selectedEquipments.find(e => e.id === this.selectedEquipment?.id);
    if (existingEquipment) {
      alert("Equipment is already in list");
      return;
    }

    this.selectedEquipments.push({
      id: this.selectedEquipment?.id,
      name: this.selectedEquipment.name,
      selectedQuantity: this.selectedQuantity
    })
  }

/**********/

  markBloodTypesAsUsed() {
    if (!this.selectedBloodType?.stockQuantity) {
      alert("Please select an bloodType");
      return;
    }

    if (this.selectedQuantity2 === 0 || this.selectedQuantity2 <= 0) {
      alert("You have to select positive number");
      return;
    }

    if (this.selectedQuantity2 > this.selectedBloodType!.stockQuantity) {
      alert("More selected than available");
      return;
    }

    const existingBloodTypes = this.selectedBloodTypes.find(e => e.id === this.selectedBloodType?.id);
    if (existingBloodTypes) {
      alert("BloodType is already in list");
      return;
    }

    this.selectedBloodTypes.push({
      id: this.selectedBloodType?.id,
      name: this.selectedBloodType.name,
      selectedQuantity: this.selectedQuantity2
    })
  }


  saveReport() {
    this.appointmentService.saveReport({
      id: this.activatedRoute.snapshot.params['id'],
      conditionsNotFulfilled: this.conditionsNotFulfilled,
      details: this.details,
      didNotShow: this.didNotShow,
      spentEquipment: this.selectedEquipments,
      spentBloodTypes:this.selectedBloodTypes,
    }).subscribe({
      next: data => {
        this.router.navigate(['/centres/' + this.centreId])
      },
      error: err => {
        console.log(err);
      }
    })
  }

  registerForAppointment() {
      this.appointmentService.registerUser(this.activatedRoute.snapshot.params['id'], AppComponent.userId).subscribe(result=>{
        alert("Uspesno zakazan termin, refreshuj stranicu :D");
        console.log("Registrovao:"+result)
      });
  }
}
