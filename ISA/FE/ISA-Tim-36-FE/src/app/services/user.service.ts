import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';

const baseUrl = 'http://localhost:8080/api/users';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) { }

  getAll(): Observable<User[]> {
    return this.http.get<User[]>(baseUrl);
  }

  getAllCentreAdmins(): Observable<User[]> {
    return this.http.get<User[]>(baseUrl + "/centre-admins");
  }

  getAllMedicalWorkersForCentre(centreId: string): Observable<User[]> {
    return this.http.get<User[]>(baseUrl + "/medical-workers/" + centreId);
  }

  get(id: any): Observable<User> {
    return this.http.get<User>(`${baseUrl}/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  createAdmin(data: any): Observable<any> {
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

  findByName(title: any): Observable<User[]> {
    return this.http.get<User[]>(`${baseUrl}?name=${name}`);
  }

  login(username: string, password: string): Observable<User> {
    return this.http.post<User>(`${baseUrl}/login`, {username, password});
  }

  changeInitialPassword(userId: string, oldPassword: string, newPassword: string): Observable<any> {
    return this.http.post<any>(`${baseUrl}/initial-password/change`, {oldPassword, newPassword, userId});
  }
}
