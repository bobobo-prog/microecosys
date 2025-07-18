import { CanActivateFn,Router } from '@angular/router';
import {inject} from '@angular/core';
import { TokenService } from '../core/token.service';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const tokenService = inject(TokenService);

  const token = tokenService.getToken();
  if(!token || tokenService.isTokenExpired())
  {
    tokenService.clearToken();
    router.navigate(['/login']);
    return false;
  }

  return true;

  
};
