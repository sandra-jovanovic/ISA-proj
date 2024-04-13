import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import AppointmentRequest from '../models/appointment-request.model';
import Appointment from '../models/appointment.model';

const baseUrl = 'http://localhost:8080/api/appointments';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  constructor(private http: HttpClient) { }

  getById(id: string): Observable<Appointment> {
    return this.http.get<Appointment>(baseUrl + "/" + id);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  registerUser(appointmentId:string, userId:number): Observable<any> {
    return this.http.post(baseUrl+"/registerUser/"+appointmentId, userId);
  }

  saveReport(data: AppointmentRequest): Observable<any> {
    return this.http.post(baseUrl + "/report", data);
  }
}
