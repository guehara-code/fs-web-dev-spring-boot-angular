import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Instructor } from 'src/app/model/instructor.model';

@Component({
  selector: 'app-courses-instructor',
  standalone: true,
  imports: [],
  templateUrl: './courses-instructor.component.html',
  styleUrls: ['./courses-instructor.component.css']
})
export class CoursesInstructorComponent implements OnInit {

  instructorId!: number;
  currentInstructor!: Instructor;

  constructor(private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.instructorId = this.route.snapshot.params['id'];
  }

  private fillCurrentInstrucotr() {
    this.currentInstructor = {
      instructorId: this.instructorId,
      firstName: "",
      lastName: "",
      summary: "",
      user: { email: "", password: "" }
    }
  }

}
