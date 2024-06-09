import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-courses-student',
  // standalone: true,
  // imports: [],
  templateUrl: './courses-student.component.html',
  styleUrls: ['./courses-student.component.css']
})
export class CoursesStudentComponent implements OnInit {

  studentId!: number;

  constructor (private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.studentId = this.route.snapshot.params['id'];    
  }
}
