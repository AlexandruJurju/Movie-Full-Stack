import {CanActivateFn, Router} from '@angular/router';
import {TokenService} from "../jwt-token/token.service";
import {inject} from "@angular/core";

export const authGuard: CanActivateFn = () => {
  const tokenService = inject(TokenService);
  const router = inject(Router);
  if (tokenService.isTokenNotValid()) {
    router.navigate(['user-login']);
    return false;
  }
  return true;
};
