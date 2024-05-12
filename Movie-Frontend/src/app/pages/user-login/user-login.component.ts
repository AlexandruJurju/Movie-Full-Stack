import {Component} from '@angular/core';
import {FormBuilder, ReactiveFormsModule, Validators} from "@angular/forms";
import {AuthenticationService} from "../../service/swagger/api/authentication.service";
import {TokenService} from "../../service/jwt-token/token.service";
import {Router} from "@angular/router";
import {LoginRequestDto} from "../../service/swagger/model/loginRequestDto";

@Component({
  selector: 'app-user-login',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './user-login.component.html',
  styleUrl: './user-login.component.css'
})

export class UserLoginComponent {

  constructor(
    private authenticationService: AuthenticationService,
    private tokenService: TokenService,
    private router: Router,
    private formBuilder: FormBuilder) {
  }

  form = this.formBuilder.nonNullable.group({
    username: ['', [Validators.required]],
    password: ['', [Validators.required]],
  })

  onSubmit() {
    console.log("login")
    if (this.form.valid) {
      const loginRequestDto: LoginRequestDto = {
        username: this.form.value.username!,
        password: this.form.value.password!,
      }
      this.login(loginRequestDto);
    }
  }


  private login(loginRequestDto: LoginRequestDto) {
    this.authenticationService.login(loginRequestDto).subscribe({
      next: (response) => {
        console.log("Login successful logged in", response);

        // save token to local storage
        this.tokenService.token = response.token;
        console.log(response.token);

        this.router.navigate(["/home"]).then(() => {
        })
      },
      error: (error) => {
        console.error('Login failed', error);
      }
    })
  }

}
