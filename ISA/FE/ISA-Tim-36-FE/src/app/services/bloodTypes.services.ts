import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import BloodTypes from '../models/bloodTypes.model';


const baseUrl = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class BloodTypesService {

  constructor(private http: HttpClient) { }

  getAvailableBloodTypes(): Observable<BloodTypes[]> {
    return this.http.get<BloodTypes[]>(baseUrl + "available-bloodTypes");
  }
}