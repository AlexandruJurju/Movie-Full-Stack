import { Component } from '@angular/core';
import {FormsModule, NgForm} from "@angular/forms";
import {GenreService} from "../../service/swagger/api/genre.service";
import {Router} from "@angular/router";
import {GenreDto} from "../../service/swagger/model/genreDto";

@Component({
  selector: 'app-genre-save',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './genre-save.component.html',
  styleUrl: './genre-save' +
    '.component.css'
})
export class GenreSaveComponent {

  constructor(
    private genreService: GenreService,
    private router: Router) {
  }

  onSubmit(genreForm: NgForm) {
    const genre: GenreDto = genreForm.value;

    this.genreService.saveGenre(genre).subscribe({
      next: (response) => {
        this.router.navigate(["/genre-list"]).then(() => {
        });
        console.log('Save successful', response);
      },
      error: (error) => {
        console.error('Save failed', error);
      }
    });
  }
}
