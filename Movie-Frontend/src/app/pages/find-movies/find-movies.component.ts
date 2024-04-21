import {Component, inject} from '@angular/core';
import {NgForOf, NgIf} from "@angular/common";
import {Movie} from "../../model/movie";
import {MovieService} from "../../service/movie.service";

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
  title = 'Movie-Frontend';
  movies: Movie[] = [];
  movieService = inject(MovieService);

  getMovies() {
    this.movieService.findAllMoviesUnpaged()
      .subscribe(movies => {
        this.movies = movies;
      });
  }
}
