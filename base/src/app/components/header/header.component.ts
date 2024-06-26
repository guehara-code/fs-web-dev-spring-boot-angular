import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { LoggedUser } from 'src/app/model/logged-user.model';
import { AuthService } from 'src/app/services/auth.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  userSub!: Subscription;
  isAuthenticated = false;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.userSub = this.authService.user.subscribe(LoggedUser => {
      this.isAuthenticated = !!LoggedUser;
    })
  }

  logout() {
    this.authService.logout();
  }

}
