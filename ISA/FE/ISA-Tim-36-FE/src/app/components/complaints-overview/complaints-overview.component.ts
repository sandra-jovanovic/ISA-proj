import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Complaint from 'src/app/models/complaint.model';
import { AuthService } from 'src/app/services/auth.service';
import { ComplaintService } from 'src/app/services/complaint.service';

@Component({
  selector: 'app-complaints-overview',
  templateUrl: './complaints-overview.component.html',
  styleUrls: ['./complaints-overview.component.css']
})
export class ComplaintsOverviewComponent implements OnInit {

  complaints: Complaint[] = [];

  constructor(private complaintService: ComplaintService, private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
   // if (!this.authService.isAdmin()) {
    //  this.router.navigate(["/login"]);
    //}

    this.getAllComplaints();
  }

  getAllComplaints() {
    this.complaintService.getAll().subscribe({
      next: data => {
        this.complaints = data;
      },
      error: err => {
        console.log(err);
      }
    })
  }

  saveAnswer(complaintId: number) {
    const complaint = this.complaints.find(x => x.id === complaintId);
    if (complaint) {
      this.complaintService.saveAnswer({
        complaintId,
        answer: complaint.answer
      }).subscribe({
        next: data => {
          console.log(data);
        },
        error: err => {
          console.log(err);
        }
      })
    }
  }

}
