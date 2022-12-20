import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {HomeComponent} from './pages/home/home.component';
import {AuthenticationComponent} from './components/authentication/authentication.component';
import {LoginComponent} from './components/login/login.component';
import {JwtInterceptor} from "./jwt.interceptor";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AuthenticationComponent,
    LoginComponent
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
      useClass: JwtInterceptor,
      multi: true
    }
  ]

  ,
  bootstrap: [AppComponent]
})
export class AppModule {
}
