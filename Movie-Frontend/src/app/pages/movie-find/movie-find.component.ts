import {Component, inject} from '@angular/core';
import {NgForOf, NgIf} from "@angular/common";
import {Movie} from "../../model/movie";
import {MovieService} from "../../service/movie.service";

@Component({
  selector: 'app-movie-find',
  standalone: true,
  imports: [
    NgForOf,
    NgIf
  ],
  templateUrl: './movie-find.component.html',
  styleUrl: './movie-find.component.css'
})
export class MovieFindComponent {
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
