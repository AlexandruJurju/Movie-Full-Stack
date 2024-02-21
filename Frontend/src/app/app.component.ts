import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {NgForOf, NgIf} from "@angular/common";
import {Movie} from "./model/movie";
import {MovieService} from "./service/movieService";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NgIf, NgForOf],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Frontend';
  movies: Movie[] = [];

  constructor(private movieService: MovieService) {
  }

  getMovies() {
    this.movieService.findAllMovies()
      .subscribe(movies => {
        this.movies = movies;
      });
  }
}
