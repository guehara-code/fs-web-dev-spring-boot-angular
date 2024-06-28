import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { variationPlacements } from '@popperjs/core';
import { Subscription } from 'rxjs';
import { Instructor } from 'src/app/model/instructor.model';
import { LoggedUser } from 'src/app/model/logged-user.model';
import { AuthService } from 'src/app/services/auth.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnDestroy {

  userSub!: Subscription;
  isAuthenticated = false;
  isInstructor = false;
  name!: string | undefined;
  currentInstructor!: Instructor | undefined;
  updateInstructorFormGroup!: FormGroup;
  
  submitted: boolean = false;

  constructor(private authService: AuthService, private fb: FormBuilder, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.userSub = this.authService.user.subscribe(loggedUser => {
      this.isAuthenticated = !!LoggedUser;
      this.isInstructor = !!loggedUser?.instructor;
      if (this.isInstructor) {
        this.name = loggedUser?.instructor?.firstName + " " + loggedUser?.instructor?.lastName;
        this.currentInstructor = loggedUser?.instructor;
      }
    })
  }

  logout() {
    this.authService.logout();
  }

  ngOnDestroy(): void {
    this.userSub.unsubscribe();
  }

  getModal(content: any) {
    this.modalService.open(content, {size: 'xl'});
    if (this.isInstructor) {
      this.updateInstructorFormGroup = this.fb.group({
        instructorId: [this.currentInstructor?.instructorId, Validators.required],
        firstName: [this.currentInstructor?.firstName, Validators.required],
        lastName: [this.currentInstructor?.lastName, Validators.required],
        summary: [this.currentInstructor?.summary, Validators.required]
      })
    }
  }

  onCloseModal(modal: any) {
    modal.close();
  }

  onUpdateInstructor(modal: any) {
    this.submitted = true;
    if(this.updateInstructorFormGroup.invalid) return;
  }

}
