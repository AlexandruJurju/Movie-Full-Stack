import {Component} from '@angular/core';
import {MovieService} from "../../service/swagger/api/movie.service";
import {Router} from "@angular/router";
import {FormsModule, NgForm} from "@angular/forms";
import {MovieDto} from "../../service/swagger/model/movieDto";

@Component({
  selector: 'app-movie-save',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './movie-save.component.html',
  styleUrl: './movie-save.component.css'
})
export class MovieSaveComponent {

  constructor(
    private movieService: MovieService,
    private router: Router) {
  }

  onSubmit(movieForm: NgForm) {
    const movie: MovieDto = movieForm.value;

    this.movieService.saveMovie(movie).subscribe({
      next: (response) => {
        this.router.navigate(["/home"]);
        console.log('Upload successful', response);
      },
      error: (error) => {
        console.error('Upload failed', error);
      }
    });
  }
}
