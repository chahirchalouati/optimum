import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {ApiInterceptor} from "./shared/interceptors/api.interceptor";
import {JwtInterceptor} from "./shared/interceptors/jwt.interceptor";
import {HomeComponent} from "./pages/home/home.component";
import {AuthorizationComponent} from "./pages/authorization/authorization.component";
import {ErrorComponent} from "./pages/error/error.component";
import {ReactiveFormsModule} from "@angular/forms";
import {NoSanitizePipe} from "./shared/pipes/no-sanitize.pipe";
import {NavbarComponent} from './components/navbar/navbar.component';
import {SidebarComponent} from './components/sidebar/sidebar.component';
import {IconComponent} from './components/icon/icon.component';
import {SearchBarComponent} from './components/search-bar/search-bar.component';
import {DropDownBoxComponent} from './components/drop-down-box/drop-down-box.component';
import {AvatarComponent} from './components/avatar/avatar.component';
import {SrcPipe} from "./shared/pipes/src.pipe";
import {ClickOutsideDirective} from './shared/directives/click-outside.directive';
import { PostCardComponent } from './components/post-card/post-card.component';
import { SideBarComponent } from './components/side-bar/side-bar.component';
import { TruncatePipe } from './shared/pipes/truncate.pipe';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AuthorizationComponent,
    ErrorComponent,
    NoSanitizePipe,
    SrcPipe,
    NavbarComponent,
    SidebarComponent,
    IconComponent,
    SearchBarComponent,
    DropDownBoxComponent,
    AvatarComponent,
    ClickOutsideDirective,
    PostCardComponent,
    SideBarComponent,
    TruncatePipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: ApiInterceptor,
    multi: true,
  },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true,
    }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
