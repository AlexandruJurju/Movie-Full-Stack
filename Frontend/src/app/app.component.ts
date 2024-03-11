import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {NgForOf, NgIf} from "@angular/common";
import {Movie} from "./model/movie";
import {MovieService} from "./service/movieService";
import {HeaderComponent} from "./components/header/header.component";
import {HomeComponent} from "./components/home/home.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NgIf, NgForOf, HeaderComponent, HomeComponent, HeaderComponent, HomeComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Frontend';
  movies: Movie[] = [];

  constructor(private movieService: MovieService) {
  }

  getMovies() {
    this.movieService.findAllMoviesUnpaged()
      .subscribe(movies => {
        this.movies = movies;
      });
  }
}
