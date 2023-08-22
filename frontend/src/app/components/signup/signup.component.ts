import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit{

  constructor(
    private userService: UserService
  ) {
  }

  ngOnInit(): void {
  }

  signup(signupForm:NgForm) {
    this.userService.signup(signupForm.value).subscribe();
  }

}
