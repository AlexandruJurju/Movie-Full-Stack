import {Component, OnInit} from '@angular/core';
import {GenreService} from "../../service/swagger/api/genre.service";
import {Router} from "@angular/router";
import {NgForOf} from "@angular/common";
import {GenreDto} from "../../service/swagger/model/genreDto";

@Component({
  selector: 'app-genre-list',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './genre-list.component.html',
  styleUrl: './genre-list.component.css'
})
export class GenreListComponent implements OnInit {
  genres: GenreDto[] = [];
  genreToDelete: GenreDto = {} as GenreDto;

  constructor(private genreService: GenreService, private router: Router) {
  }

  ngOnInit() {
    this.getGenres();
  }

  getGenres(): void {
    this.genreService.getAllGenres().subscribe(genres => this.genres = genres);
  }

  // TODO: pass the whole object to page, not just the id, get rid of the call
  editGenre(genre: GenreDto): void {
    this.router.navigate(['/genre-edit', genre.id]);
  }

  deleteGenre(genreId: number): void {
    this.genreService.deleteGenreById(genreId).subscribe(() => {
      this.getGenres(); // Refresh the list after deletion
    });
  }

  addGenre(): void {
    this.router.navigate(["/genre-save"])
  }

  setGenreToDelete(genre: GenreDto) {
    this.genreToDelete = genre;
  }
}
