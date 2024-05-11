import {Component, OnInit} from '@angular/core';
import {FormsModule, NgForm} from "@angular/forms";
import {MovieService} from "../../service/swagger/api/movie.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MovieDto} from "../../service/swagger/model/movieDto";
import {GenreDto} from "../../service/swagger/model/genreDto";

@Component({
  selector: 'app-movie-edit',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './movie-edit.component.html',
  styleUrl: './movie-edit.component.css'
})
export class MovieEditComponent implements OnInit {
  movie: MovieDto = {} as MovieDto;
  movieCopy: MovieDto = {} as MovieDto;
  genres: GenreDto[] = [];

  constructor(private movieService: MovieService,
              private router: Router,
              private route: ActivatedRoute,) {
  }

  ngOnInit() {
    const movieId = +this.route.snapshot.paramMap.get('id')!;
    if (movieId === null || isNaN(movieId)) {
      this.router.navigate(['/error']).then(() => {
      });
      return;
    }

    console.log(movieId);

    this.movieService.findMovieById(movieId).subscribe({
      next: movie => {
        if (!movie) {
          this.router.navigate(['/error']).then(() => {
          });
          return;
        }
        this.movie = movie;
        this.movieCopy = {...movie};
      },
      error: error => {
        console.error('Error fetching movie:', error);
        this.router.navigate(['/error']).then(() => {
        });
      }
    });

    this.movieService.findGenresOfMovie(movieId).subscribe({
      next: genres => {
        this.genres = genres;
      },
      error: error => {
        console.error('Error fetching genres:', error);
      }
    });

  }

  onSubmit(form: NgForm) {
    if (form.valid) {
      this.movieService.updateMovie(this.movie, this.movie.id!).subscribe({
        next: () => {
          this.router.navigate(["/movie-details", this.movie.id]).then(() => {
          });
        },
        error: error => {
          this.router.navigate(["/error"]).then(() => {
          });
          console.error('Error updating movie:', error);
        }
      });
    }
  }

  deleteMovieById(movieId: number) {
    this.movieService.deleteMovieById(movieId).subscribe({
      next: () => {
        this.router.navigate(["/movie-get"]).then(() => {
        });
      },
      error: error => {
        this.router.navigate(["/error"]).then(() => {
        })
      }
    })
  }

  resetForm() {
    this.movie = {...this.movieCopy};
  }

}
