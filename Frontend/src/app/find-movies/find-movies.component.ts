import {Component} from '@angular/core';
import {NgForOf, NgIf} from "@angular/common";
import {MovieService} from "../service/movie.service";
import {Movie} from "../model/movie";

@Component({
  selector: 'app-find-movies',
  standalone: true,
  imports: [
    NgForOf,
    NgIf
  ],
  templateUrl: './find-movies.component.html',
  styleUrl: './find-movies.component.css'
})
export class FindMoviesComponent {
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
