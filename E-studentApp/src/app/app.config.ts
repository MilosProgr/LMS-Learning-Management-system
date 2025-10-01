// Kombinirajući sve ove elemente u appConfig,
// definirate konfiguraciju vaše Angular aplikacije koja uključuje rutiranje, usluge i modul za HTTP komunikaciju. 

import { ApplicationConfig, importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { PredmetService } from '../Services/predmet.service';
import { HttpClient, HttpClientModule, provideHttpClient, withFetch, withInterceptors } from '@angular/common/http';
import { authInterceptor } from './interceptors/auth.interceptor';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { provideAnimations } from '@angular/platform-browser/animations';
import { MatNativeDateModule } from '@angular/material/core';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';

export function HttpLoaderFactory(http: HttpClient): TranslateLoader {
  return new TranslateHttpLoader(http);
}

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    PredmetService,
    importProvidersFrom(
      MatNativeDateModule,
      HttpClientModule,
      CalendarModule.forRoot({ provide: DateAdapter, useFactory: adapterFactory }),
      TranslateModule.forRoot({
        loader: {
          provide: TranslateLoader,
          useFactory: HttpLoaderFactory,
          deps: [HttpClient]
        }
      })
    ),
    provideHttpClient(withFetch(), withInterceptors([authInterceptor])),
    provideAnimations() //potrebno za mat biblioteku
  ]
};
