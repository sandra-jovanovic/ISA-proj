import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { Centre } from 'src/app/models/centre.model';
import { CentresService } from 'src/app/services/centres.service';
import { MatLegacyTableDataSource as MatTableDataSource, MatLegacyTableModule as MatTableModule } from '@angular/material/legacy-table';
import {MatSort, Sort, MatSortModule} from '@angular/material/sort';
import {MatLegacyPaginator as MatPaginator} from '@angular/material/legacy-paginator';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements AfterViewInit {

  displayedColumns: string[] = ['name', 'streetName', 'description', 'averageRate'];
 
  centres = new MatTableDataSource<Centre[]>;
  currentCentre: Centre = {};
  currentIndex = -1;
  streetName ='';
  name='';

  constructor(private login: CentresService) { }

  @ViewChild(MatSort)
  sort: MatSort = new MatSort;
  
  ngAfterViewInit(): void {
    this.retrieveCentres();
    this.centres.sort = this.sort;
  }

  retrieveCentres(): void {
    this.login.getAll()
      .subscribe({
        next: (data) => {
          this.centres = new MatTableDataSource(<Centre[][]>data);
          this.centres.sort = this.sort;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  setActiveCentre(login: Centre, index: number): void {
    this.currentCentre = login;
    this.currentIndex = index;
  }

  filterByNameAndSurname(): void {
    this.login.getAll()
      .subscribe({
        next: (data) => {
          this.centres = new MatTableDataSource(<any>data.filter(centres => centres.name?.includes(this.name) && centres.streetName?.includes(this.streetName)));
        },
        error: (e) => console.error(e)
      });
  }


}
