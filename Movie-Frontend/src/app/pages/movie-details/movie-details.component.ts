import {Component, OnInit} from '@angular/core';
import {Movie} from "../../service/model/movie";
import {Genre} from "../../service/model/genre";
import {MovieService} from "../../service/api/movie.service";
import {ActivatedRoute, Router} from "@angular/router";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-movie-details',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './movie-details.component.html',
  styleUrl: './movie-details.component.css'
})

export class MovieDetailsComponent implements OnInit {
  movie: Movie = {} as Movie;
  genres: Genre[] = [];

  constructor(private movieService: MovieService,
              private router: Router,
              private route: ActivatedRoute,) {
  }

  ngOnInit() {
    const movieId = +this.route.snapshot.paramMap.get('id')!;
    if (movieId === null || isNaN(movieId)) {
      this.router.navigate(['/error']);
      return;
    }

    console.log(movieId);

    this.movieService.findMovieById(movieId).subscribe({
      next: movie => {
        if (!movie) {
          this.router.navigate(['/error']);
          return;
        }
        this.movie = movie;
      },
      error: error => {
        console.error('Error fetching movie:', error);
        this.router.navigate(['/error']);
      }
    });

    this.movieService.findGenresOfMovie(movieId).subscribe({
      next: genres => {
        console.log(genres);
        this.genres = genres;
      },
      error: error => {
        console.error('Error fetching genres:', error);
      }
    });
  }

  // TODO: pass the movie object, not the id
  navigateToUpdateMovie() {
    this.router.navigate(['/movie-edit', this.movie.id]);
  }
}
