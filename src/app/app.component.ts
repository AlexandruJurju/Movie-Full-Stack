import {Component, OnInit} from '@angular/core';
import {Movie} from "./model/movie";
import {MovieService} from "./service/movie.service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  public title: string = 'Frontend';
  public movies: Movie[] = [];

  constructor(private movieService: MovieService) {
  }


  ngOnInit() {
    this.getMovies();
  }

  private getMovies(): void {
    this.movieService.getMovies().subscribe({
      next: (res: Movie[]) => {
        this.movies = res
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message)
      }
    })
  }

}
