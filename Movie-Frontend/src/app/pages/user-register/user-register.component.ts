import {Component, inject} from '@angular/core';
import {FormBuilder, ReactiveFormsModule, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {RegisterRequestDto} from "../../model/registerRequestDto";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-user-register',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './user-register.component.html',
  styleUrl: './user-register.component.css'
})
export class UserRegisterComponent {
  fb = inject(FormBuilder);
  authService = inject(AuthService);
  router = inject(Router);

  // validate form data
  form = this.fb.nonNullable.group({
    username: ['', Validators.required],
    email: ['', Validators.required],
    password: ['', Validators.required]
  });

  onSubmit(): void {
    if (this.form.valid) {
      const formValue = this.form.value;
      if (formValue.username && formValue.email && formValue.password) {
        const registerRequestDto: RegisterRequestDto = {
          username: formValue.username,
          email: formValue.email,
          password: formValue.password
        };

        this.authService.register(registerRequestDto).subscribe({
          next: (response) => {
            console.log('Registration successful', response);

            // set the JWT token to local storage
            localStorage.setItem('token', response.token);

            // signal as authenticated
            this.authService.currentUserSig.set(response)
            this.router.navigateByUrl('/')
          },
          error: (error) => {
            console.error('Registration failed', error);
          }
        });

      }
    }
  }

}
