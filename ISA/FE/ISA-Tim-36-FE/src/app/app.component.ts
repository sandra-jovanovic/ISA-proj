import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  static get role(): string {
    return this._role;
  }
  isAdmin(): boolean
  {
    return AppComponent.role === 'ADMINISTRATOR_SISTEMA';
  }
  
  isMedicalAdmin(): boolean
  {
    return AppComponent.role === 'ADMINISTRATOR_CENTRA_I_MEDICINSKO_OSOBLJE';
  }
  isLogedIn():boolean
  {
    return AppComponent.userId != -1;
  }

  static set role(value: string) {
    this._role = value;
  }
  title = 'ISA-Tim-36-FE';
  private static _role:string = "";
  public static userId:number = -1;
}
