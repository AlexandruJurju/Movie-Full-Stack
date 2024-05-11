import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {NgForOf, NgIf} from "@angular/common";
import {MovieService} from "../../service/swagger/api/movie.service";
import {MovieDto} from "../../service/swagger/model/movieDto";

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
  movies: MovieDto[] = [];

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