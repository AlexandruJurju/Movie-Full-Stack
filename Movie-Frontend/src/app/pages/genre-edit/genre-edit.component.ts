import {Component, OnInit} from '@angular/core';
import {FormsModule, NgForm} from "@angular/forms";
import {GenreDto} from "../../service/swagger/model/genreDto";
import {GenreService} from "../../service/swagger/api/genre.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-genre-edit',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './genre-edit.component.html',
  styleUrl: './genre-edit.component.css'
})

export class GenreEditComponent implements OnInit {
  genre: GenreDto = {} as GenreDto;

  constructor(private genreService: GenreService,
              private router: Router,
              private route: ActivatedRoute,) {
  }

  ngOnInit() {
    this.findGenreByPathId();
  }

  private findGenreByPathId() {
    const genreId = +this.route.snapshot.paramMap.get('id')!;
    if (genreId === null || isNaN(genreId)) {
      this.router.navigate(['/error']).then(() => {
      });
      return;
    }

    console.log(genreId);

    this.genreService.findGenreById(genreId).subscribe({
      next: genre => {
        if (!genre) {
          this.router.navigate(['/error']).then(() => {
          });
          return;
        }
        this.genre = genre;
      },
      error: error => {
        console.error('Error fetching genre:', error);
        this.router.navigate(['/error']).then(() => {
        });
      }
    });
  }

  onSubmit(form: NgForm) {
    if (form.valid) {
      this.genreService.updateGenre(this.genre, this.genre.id).subscribe({
        next: () => {
          this.router.navigate(["/genre-list"]).then(() => {
          });
        },
        error: error => {
          this.router.navigate(["/error"]).then(() => {
          });
          console.error('Error updating genre:', error);
        }
      });
    }
  }

}
