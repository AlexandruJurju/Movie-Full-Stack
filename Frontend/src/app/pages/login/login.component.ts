import {Component, inject} from '@angular/core';
import {FormBuilder, ReactiveFormsModule, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {LoginRequestDto} from "../../model/loginRequestDto";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})

export class LoginComponent {
  authService = inject(AuthService);
  fb = inject(FormBuilder);
  router = inject(Router)

  form = this.fb.nonNullable.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  });

  onSubmit(): void {
    console.log("login")
    if (this.form.valid) {
      const formValue = this.form.value;
      if (formValue.username && formValue.password) {
        const loginRequestDto: LoginRequestDto = {
          username: formValue.username,
          password: formValue.password
        };

        this.authService.login(loginRequestDto).subscribe({
          next: (response) => {
            console.log('Login successful', response);

            // set the JWT token to local storage
            localStorage.setItem('token', response.token);

            // signal as authenticated
            this.authService.currentUserSig.set(response)

            this.router.navigateByUrl('/')
          },
          error: (error) => {
            console.error('Login failed', error);
          }
        });

      }
    }
  }
}
