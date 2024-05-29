import {Component, OnInit} from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CoursesService } from 'src/app/services/courses.service';
import { Observable, catchError, throwError } from 'rxjs';
import { PageResponse } from 'src/app/model/page.response.model';
import { Course } from '../../model/course.model';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})

export class CoursesComponent implements OnInit {

  searchFormGroup!: FormGroup;
  pageCourses$!: Observable<PageResponse<Course>>;
  currentPage: number = 0;
  pageSize: number = 5;
  errorMessage!: string;

  constructor(private modalService: NgbModal, 
    private fb: FormBuilder, private courseService: CoursesService) {
  }

  ngOnInit(): void {
    this.searchFormGroup = this.fb.group({
      keyword: this.fb.control('')
    })
    this.handleSearchCourses()
  }


  getModal(content: any) {
    this.modalService.open(content, {size: 'xl'})
    console.log("Hello world")
  }

  handleSearchCourses() {
  
    let keyword = this.searchFormGroup.value.keyword;
    this.pageCourses$ = this.courseService.searchCourses(keyword, this.currentPage, this.pageSize).pipe(
      catchError(err => {
        this.errorMessage = err.message;
        return throwError(err);
      })
    )
    
  }

  gotoPage(page: number) {
    this.currentPage = page;
    this.handleSearchCourses();
  }

  handleDeleteCourse(c: Course) {
    let conf = confirm("Are you sure?")
    if (!conf) return;    
  }

}
