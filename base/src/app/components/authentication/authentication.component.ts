import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-authentication',
  // standalone: true,
  // imports: [],
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css']
})
export class AuthenticationComponent implements OnInit {


  loginFormGroup!: FormGroup;
  submitted: boolean = false;

  constructor(private fb: FormBuilder) {

  }

  ngOnInit(): void {
    this.loginFormGroup = this.fb.group({
      username: ["", [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
      password: ["", Validators.required]
    })
  }

  onLogin() {
    this.submitted = true;
    if (this.loginFormGroup.invalid) return;
  }

}
