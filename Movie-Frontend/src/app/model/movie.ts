export class Movie {
  id: number;
  title: string;
  tagline: string;
  overview: string;
  runtimeInMinutes: number;
  posterUrl: string | null;
  imdbUrl: string | null;
  imdbRating: number | null;
  trailerUrl: string | null;
  releaseDate: Date | null;


  constructor(id: number, title: string, tagline: string, overview: string, runtimeInMinutes: number, posterUrl: string, imdbUrl: string, imdbRating: number, trailerUrl: string, releaseDate: Date) {
    this.id = id;
    this.title = title;
    this.tagline = tagline;
    this.overview = overview;
    this.runtimeInMinutes = runtimeInMinutes;
    this.posterUrl = posterUrl;
    this.imdbUrl = imdbUrl;
    this.imdbRating = imdbRating;
    this.trailerUrl = trailerUrl;
    this.releaseDate = releaseDate;
  }
}
