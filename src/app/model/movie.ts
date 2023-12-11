import {Genre} from "./genre";

export interface Movie {
  id: number;
  title: string;
  tagline?: string;
  overview?: string;
  runtime?: number;
  revenue?: number;
  budget?: number;
  releaseStatus?: ReleaseStatus;
  posterURL?: string;
  releaseDate?: Date;
  genres?: Array<Genre>;
}

export enum ReleaseStatus {
  RELEASED = "RELEASED",
  UPCOMING = "UPCOMING",
  OTHER = "OTHER"
}
