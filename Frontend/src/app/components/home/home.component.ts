import {Component} from '@angular/core';
import {NgForOf, NgIf} from "@angular/common";
import {MovieService} from "../../service/movieService";
import {Movie} from "../../model/movie";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    NgForOf,
    NgIf
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  movies: Movie[] = [];
  title: string = "Home"

  constructor(private movieService: MovieService) {
  }

  getMovies() {
    this.movieService.findAllMoviesUnpaged()
      .subscribe(movies => {
        this.movies = movies;
      });
  }
}
