import {Injectable, signal} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LoginRequestDto} from "../model/loginRequestDto";
import {Observable} from "rxjs";
import {LoginResponseDto} from "../model/loginResponseDto";
import {RegisterRequestDto} from "../model/registerRequestDto";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  // initially user is null - unauthorized, after login signal receives LoginResponseDto
  currentUserSig = signal<LoginResponseDto | null>(null);

  private readonly baseUrl = 'http://localhost:8080/api/v1/auth';

  constructor(private http: HttpClient) {
  }

  register(registerRequestDto: RegisterRequestDto): Observable<LoginResponseDto> {
    return this.http.post<LoginResponseDto>(`${this.baseUrl}/register`, registerRequestDto);
  }

  login(loginRequestDto: LoginRequestDto): Observable<LoginResponseDto> {
    return this.http.post<LoginResponseDto>(`${this.baseUrl}/login`, loginRequestDto);
  }
}
