import {Component, OnInit} from '@angular/core';
import { UserAuthService } from '../../services/user-auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit{

  constructor(private userAuthService: UserAuthService, private router: Router) {
  }

  ngOnInit(): void {
    this.isAdmin();
  }

  private isAdmin() {
    if (this.userAuthService.getRole() !== 'ADMIN') {
      this.router.navigate(['/login']);
    }
  }

}
