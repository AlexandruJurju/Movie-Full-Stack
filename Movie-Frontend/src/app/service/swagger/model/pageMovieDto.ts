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
import { MovieDto } from './movieDto';
import { PageableObject } from './pageableObject';
import { SortObject } from './sortObject';

export interface PageMovieDto { 
    totalPages?: number;
    totalElements?: number;
    numberOfElements?: number;
    size?: number;
    content?: Array<MovieDto>;
    number?: number;
    sort?: SortObject;
    first?: boolean;
    last?: boolean;
    pageable?: PageableObject;
    empty?: boolean;
}