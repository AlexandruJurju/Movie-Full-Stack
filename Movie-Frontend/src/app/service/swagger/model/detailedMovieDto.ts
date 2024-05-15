/**
 * Movie REST API for full stack application
 * A backend rest api program for a full stack project
 *
 * OpenAPI spec version: v1
 * Contact: alexandru.i.jurju@gmail.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
import { GenreDto } from './genreDto';
import { MovieActorDto } from './movieActorDto';

export interface DetailedMovieDto { 
    id: number;
    title: string;
    headline: string;
    overview: string;
    runtimeInMinutes?: number;
    posterUrl: string;
    releaseDate: string;
    releaseStatus: DetailedMovieDto.ReleaseStatusEnum;
    genres: Array<GenreDto>;
    movieActors: Array<MovieActorDto>;
}
export namespace DetailedMovieDto {
    export type ReleaseStatusEnum = 'RELEASED' | 'UPCOMING' | 'OTHER';
    export const ReleaseStatusEnum = {
        RELEASED: 'RELEASED' as ReleaseStatusEnum,
        UPCOMING: 'UPCOMING' as ReleaseStatusEnum,
        OTHER: 'OTHER' as ReleaseStatusEnum
    };
}