import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import Equipment from '../models/equipment.models';

const baseUrl = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class EquipmentService {

  constructor(private http: HttpClient) { }

  getAvailableEquipment(): Observable<Equipment[]> {
    return this.http.get<Equipment[]>(baseUrl + "available-equipment");
  }
}
