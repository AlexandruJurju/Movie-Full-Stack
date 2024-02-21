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

  findAllMovies(): Observable<Movie[]> {
    return this.http.get<Movie[]>(this.baseUrl + '/unpaged'); // Append '/unpaged' to base URL
  }
}
