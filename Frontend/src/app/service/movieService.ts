import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Movie} from "../model/movie";

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  private readonly baseUrl = 'http://localhost:8080/api/v1/movie'; // Use readonly instead of private

  constructor(private http: HttpClient) {
  }

  // TODO: implement all methods from service
  // TODO: add page for edit
  // TODO: add page for upload
  // TODO: add page for searching
  // TODO: add filtering
  findAllMoviesUnpaged(): Observable<Movie[]> {
    return this.http.get<Movie[]>(this.baseUrl + '/unpaged'); // Append '/unpaged' to base URL
  }

  findMovieById(movieId: number): Observable<Movie> {
    return this.http.get<Movie>(`${this.baseUrl}/${movieId}`)
  }

  findMoviesByReleaseStatus(status: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/status/${status}`);
  }

  findMoviesByReleaseYear(year: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/year/${year}`);
  }

  saveMovie(movie: Movie): Observable<Movie> {
    return this.http.post<Movie>(this.baseUrl, movie)
  }

  deleteMovie(movieId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${movieId}`);
  }

  updateMovie(movie: Movie): Observable<Movie> {
    return this.http.put<Movie>(this.baseUrl, movie);
  }

  deleteMovieById(movieId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${movieId}`);
  }
}
