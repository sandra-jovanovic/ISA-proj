import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs/internal/Observable';
import Appointment from 'src/app/models/appointment.model';
import { Centre } from 'src/app/models/centre.model';
import { User } from 'src/app/models/user.model';
import { AppointmentService } from 'src/app/services/appointment.service';
import { CentresService } from 'src/app/services/centres.service';
import { UserService } from 'src/app/services/user.service';
import {AppComponent} from "../../app.component";

@Component({
  selector: 'app-centre-details',
  templateUrl: './centre-details.component.html',
  styleUrls: ['./centre-details.component.css']
})
export class CentreDetailsComponent implements OnInit {

  @Input() viewMode = false;

  centreAdmins: User[] = [];
  administrator: number = 0;
  appointments$: Observable<Appointment[]> = new Observable();

  isAdmin(): boolean
  {
    return AppComponent.role === 'ADMINISTRATOR_SISTEMA';
  }

  isMedicalAdmin(): boolean
  {
    return AppComponent.role === 'ADMINISTRATOR_CENTRA_I_MEDICINSKO_OSOBLJE';
  }
  @Input() currentCentre: Centre = {
    name: '',
    averageRate: 0,
    description: '',
    id: 0,
    streetName: '',
    streetNumber: '',
    administrator:0,
  };

  newAppointmentForm = new FormGroup({
    dateAndTime: new FormControl('', [Validators.required]),
    duration: new FormControl('', [Validators.required]),
    medicalWorker: new FormControl('', [Validators.required])
  });

  message = '';

  medicalWorkers: User[] = [];

  constructor(
    private centresService: CentresService,
    private usersService: UserService,
    private appointmentsService: AppointmentService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getCentre(this.route.snapshot.params["id"]);
      this.getAppointments(this.route.snapshot.params["id"]);
      this.getMedicalWorkers(this.route.snapshot.params["id"]);
    }

  }

  getCentre(id: string): void {
    this.centresService.get(id)
      .subscribe({
        next: (data) => {
          this.currentCentre = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }


  getAppointments(centreId: string) {
    this.appointments$ = this.centresService.getAppointmentsByCentre(centreId);
  }

  getMedicalWorkers(centreId: string) {
    this.usersService.getAllMedicalWorkersForCentre(centreId)
    .subscribe({
      next: data => {
        this.medicalWorkers = data;
      },
      error: error => {
        console.log(error);
      }
    })
  }

  updatePublished(status: boolean): void {
    const data = {
      name: this.currentCentre.name,
      averageRate: this.currentCentre.averageRate,
      description: this.currentCentre.description,
      streetName: this.currentCentre.streetName,
      streetNumber: this.currentCentre.streetNumber,
      administrator:this.currentCentre.administrator,




    };

    this.message = '';

    this.centresService.update(this.currentCentre.id, data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message ? res.message : 'The status was updated successfully!';
        },
        error: (e) => console.error(e)
      });
  }

  updateCentre(): void {
    this.message = '';

    if(confirm("Are you sure to update "+this.currentCentre.name))
      {
      this.centresService.update(this.currentCentre.id, this.currentCentre)
        .subscribe({
          next: (res) => {
            console.log(res);
            this.message = res.message ? res.message : 'This center was updated successfully!';
          },
          error: (e) => {
            console.error(e);
            this.message = "Validation not good!";
          }
              });
    }
  }

  deleteCentre(): void
  {
    if(confirm("Are you sure to delete "+this.currentCentre.name))
    {
      console.log("Implement delete functionality here");

      this.centresService.delete(this.currentCentre.id)
        .subscribe({
          next: (res) => {
            console.log(res);
            this.router.navigate(['/centres']);
          },
          error: (e) => console.error(e)
        });
    }
  }

  onAppointmentAddFormSubmit() {
    this.appointmentsService.create({
        ...this.newAppointmentForm.value,
        centre: this.route.snapshot.params["id"]
      })
      .subscribe({
        next: data => {
          alert('Appointment successfully saved');
          this.newAppointmentForm.reset();
        },
        error: (err: HttpErrorResponse) => {
          if (err.status === 400) {
              alert('Appointment already booked at time you tried to select')
          }
          console.log(err);
        }
      })
  }


}
