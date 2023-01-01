import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AuthorizationComponent} from './pages/authorization/authorization.component';
import {HomeComponent} from './pages/home/home.component';
import {ApiInterceptor} from "./shared/interceptors/api.interceptor";
import {JwtInterceptor} from "./shared/interceptors/jwt.interceptor";
import {ErrorComponent} from './pages/error/error.component';

@NgModule({
  declarations: [
    AppComponent,
    AuthorizationComponent,
    HomeComponent,
    ErrorComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ApiInterceptor,
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true,
    }

  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
