import {Component} from '@angular/core';
import {FormsModule, NgForm} from "@angular/forms";
import {MovieService} from "../service/movieService";
import {Movie} from "../model/movie";

@Component({
  selector: 'app-upload-movie',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './upload-movie.component.html',
  styleUrl: './upload-movie.component.css'
})
export class UploadMovieComponent {


  constructor(private movieService: MovieService) {
  }

  onSubmit(form: NgForm) {
    const movie: Movie = form.value;
    this.movieService.saveMovie(movie).subscribe({
      next: (response) => {
        console.log('Upload successful', response);
      },
      error: (error) => {
        console.error('Upload failed', error);
      }
    });
  }

  onFileSelected(event: Event) {
    const inputElement = event.target as HTMLInputElement;
    const fileList = inputElement.files;
    if (fileList && fileList.length > 0) {
      // Display preview of the selected poster image if needed
      const posterPreview = document.getElementById('posterPreview') as HTMLImageElement;
      posterPreview.src = URL.createObjectURL(fileList[0]);
    }
  }
}
