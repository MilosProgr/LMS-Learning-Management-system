import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const token = sessionStorage.getItem("token")
  const cloneRequest = req.clone({

    setHeaders: {
      Authorization: `${token}`
    }
  });


  return next(cloneRequest);
};
