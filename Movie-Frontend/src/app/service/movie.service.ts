import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Movie} from "../model/movie";
import {BASE_URL} from "../config";

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  constructor(private http: HttpClient) {
  }

  findAllMoviesUnpaged(): Observable<Movie[]> {
    return this.http.get<Movie[]>(`${BASE_URL}/api/v1/movie/unpaged`);
  }

  findMovieById(movieId: number): Observable<Movie> {
    return this.http.get<Movie>(`${BASE_URL}/api/v1/movie/${movieId}`);
  }

  findMoviesByReleaseStatus(status: string): Observable<any> {
    return this.http.get(`${BASE_URL}/api/v1/movie/status/${status}`);
  }

  findMoviesByReleaseYear(year: number): Observable<any> {
    return this.http.get(`${BASE_URL}/api/v1/movie/year/${year}`);
  }

  saveMovie(movie: Movie): Observable<Movie> {
    return this.http.post<Movie>(`${BASE_URL}/api/v1/movie`, movie);
  }

  deleteMovie(movieId: number): Observable<void> {
    return this.http.delete<void>(`${BASE_URL}/api/v1/movie/${movieId}`);
  }

  updateMovie(movie: Movie): Observable<Movie> {
    return this.http.put<Movie>(`${BASE_URL}/api/v1/movie`, movie);
  }

  deleteMovieById(movieId: number): Observable<any> {
    return this.http.delete(`${BASE_URL}/api/v1/movie/${movieId}`);
  }
}
