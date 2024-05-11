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
  page: number = 0;
  size: number = 6;
  pages: number[] = [];

  constructor(private movieService: MovieService, private router: Router) {
  }

  ngOnInit() {
    this.findMoviesPaged();
  }

  private findMoviesPaged() {
    this.movieService.findMoviesPaged(this.page, this.size)
      .subscribe({
        next: (pagedMovies) => {
          this.movies = pagedMovies.content!;
          this.pages = Array(pagedMovies.totalPages).fill(0).map((x, i) => i); // create an array for the pages
        }
      });
  }

  goToPage(page: number) {
    this.page = page;
    this.findMoviesPaged();
  }

  goToMovieDetails(id: number) {
    this.router.navigate(['/movie-details', id]);
  }
}
