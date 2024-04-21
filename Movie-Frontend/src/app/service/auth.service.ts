import { Injectable, signal } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { LoginRequestDto } from "../model/loginRequestDto";
import { Observable } from "rxjs";
import { LoginResponseDto } from "../model/loginResponseDto";
import { RegisterRequestDto } from "../model/registerRequestDto";
import { BASE_URL } from "../config";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  // initially signal is null - unauthorized, after user-login signal receives LoginResponseDto
  currentUserSig = signal<LoginResponseDto | null>(null);

  constructor(private http: HttpClient) {
  }

  register(registerRequestDto: RegisterRequestDto): Observable<LoginResponseDto> {
    return this.http.post<LoginResponseDto>(`${BASE_URL}/api/v1/auth/register`, registerRequestDto);
  }

  login(loginRequestDto: LoginRequestDto): Observable<LoginResponseDto> {
    return this.http.post<LoginResponseDto>(`${BASE_URL}/api/v1/auth/login`, loginRequestDto);
  }

  status(token: string): Observable<LoginResponseDto> {
    return this.http.post<LoginResponseDto>(`${BASE_URL}/api/v1/auth/status`, token);
  }
}
