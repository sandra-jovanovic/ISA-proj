import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import Appointment from '../models/appointment.model';
import { CentrePost, Centre } from '../models/centre.model';
import { User } from '../models/user.model';
import {appointmentSearchDTO} from "../models/appointmentSearchDTO.model";

const baseUrl = 'http://localhost:8080/api/centres';

@Injectable({
  providedIn: 'root'
})
export class CentresService {

  constructor(private http: HttpClient) {}

  get(id: any): Observable<Centre> {
    return this.http.get<Centre>(`${baseUrl}/${id}`);
  }

  getAll(): Observable<Centre[]> {
    return this.http.get<Centre[]>(baseUrl);
  }

  getAllByTime(data:appointmentSearchDTO): Observable<Centre[]> {
    console.log(data);
    return this.http.post<Centre[]>(baseUrl+"/getbytime", data);
  }
  create(data: CentrePost): Observable<any> {
    return this.http.post(baseUrl, data);
  }


  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }

  findByName(title: any): Observable<Centre[]> {
    return this.http.get<Centre[]>(`${baseUrl}?name=${name}`);
  }


  //da dobavljanje admina
  getAllCentreAdmins(): Observable<User[]> {
    return this.http.get<User[]>(baseUrl + "/centre-admins");
  }

  getAppointmentsByCentre(centreId: string): Observable<Appointment[]> {
    return this.http.get<Appointment[]>(`${baseUrl}/${centreId}/appointments`);
  }


}
