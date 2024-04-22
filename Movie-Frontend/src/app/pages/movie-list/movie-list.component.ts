import {Component, OnInit} from '@angular/core';
import {Movie} from "../../service/model/movie";
import {Router} from "@angular/router";
import {MovieService} from "../../service/api/movie.service";
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-movie-list',
  standalone: true,
  imports: [
    NgForOf,
    NgIf
  ],
  templateUrl: './movie-list.component.html',
  styleUrl: './movie-list.component.css'
})
export class MovieListComponent implements OnInit {
  movies: Movie[] = [];

  constructor(private movieService: MovieService, private router: Router) {
  }

  ngOnInit() {
    this.getMovies();
  }

  getMovies() {
    this.movieService.findAllMovies()
      .subscribe(movies => {
        this.movies = movies;
      })
  }

  goToMovieDetails(id: number) {
    this.router.navigate(['/movie-details', id]);
  }
}
