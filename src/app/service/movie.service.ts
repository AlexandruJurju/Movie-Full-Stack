import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Movie} from "../model/movie";

@Injectable({
  providedIn: 'root'
})

export class MovieService {
  private apiServerUrl = 'http://localhost:8080/';

  constructor(private http: HttpClient) {
  }

  public getMovies(): Observable<Movie[]> {
    return this.http.get<Movie[]>(`${this.apiServerUrl}/movie`);
  }

  public getMovie(movieId: number): Observable<Movie> {
    return this.http.get<Movie>(`${this.apiServerUrl}/movie/${movieId}`)
  }

  public addMovie(movie: Movie): Observable<Movie> {
    return this.http.post<Movie>(`${this.apiServerUrl}/movie`, movie);
  }

  public editMovie(movie: Movie): Observable<Movie> {
    return this.http.put<Movie>(`${this.apiServerUrl}/movie`, movie);
  }

  public deleteMovie(movieId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/movie/${movieId}`);
  }

}
