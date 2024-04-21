import {Component, inject, OnInit} from '@angular/core';
import {NgForOf, NgIf} from "@angular/common";
import {HeaderComponent} from "./header/header.component";
import {HomeComponent} from "./pages/home/home.component";
import {UploadMovieComponent} from "./pages/upload-movie/upload-movie.component";
import {RouterLink, RouterOutlet} from "@angular/router";
import {FindMoviesComponent} from "./pages/find-movies/find-movies.component";
import {AuthService} from "./service/auth.service";


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    NgIf,
    NgForOf,
    HeaderComponent,
    HomeComponent,
    UploadMovieComponent,
    RouterOutlet,
    RouterLink,
    FindMoviesComponent,
  ]
  ,
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

  authService = inject(AuthService);

  ngOnInit(): void {
    const token = localStorage.getItem('token');

    if (token && token !== '') {
      this.authService.status(token).subscribe({
        next: (response) => {
          console.log('User already exists', response);
          this.authService.currentUserSig.set(response)
        },
        error: () => {
          this.authService.currentUserSig.set(null);
        }
      });
    }
  }
}