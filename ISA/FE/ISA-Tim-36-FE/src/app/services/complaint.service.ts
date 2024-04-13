import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import Complaint from '../models/complaint.model';
import { Observable } from 'rxjs';

const baseUrl = 'http://localhost:8080/api/complaints/';

@Injectable({
  providedIn: 'root'
})
export class ComplaintService {

  constructor(private http: HttpClient) {}

  getAll(): Observable<Complaint[]> {
    return this.http.get<Complaint[]>(`${baseUrl}`);
  }

  saveAnswer(body: any) {
    return this.http.put(`${baseUrl}`, body);
  }
}
