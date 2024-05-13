import {HttpInterceptorFn} from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  // get token from localstorage
  const token: string = localStorage.getItem('token') ?? '';

  // set add the bearer token for every request before sending it
  req = req.clone({
    setHeaders: {
      Authorization: token ? `Bearer ${token}` : '',
    }
  })

  return next(req);
};
