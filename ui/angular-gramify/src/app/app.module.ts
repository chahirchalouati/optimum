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
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NoSanitizePipe} from "./shared/pipes/no-sanitize.pipe";
import {SrcPipe} from "./shared/pipes/src.pipe";
import {ClickOutsideDirective} from './shared/directives/click-outside.directive';
import {TruncatePipe} from './shared/pipes/truncate.pipe';
import {IconSizePipe} from './shared/pipes/icon-size.pipe';
import {ModalDirective} from './shared/directives/modal.directive';
import {DynamicComponentDirective} from './shared/directives/dynamic-component.directive';
import {ImgFallbackDirective} from './shared/directives/img-fallback.directive';
import {TrackVisibilityDirective} from './shared/directives/track-visibility.directive';
import {IndexedPipe} from './shared/pipes/indexed.pipe';
import {CommonModule, NgOptimizedImage, registerLocaleData} from "@angular/common";
import {it_IT, NZ_I18N} from 'ng-zorro-antd/i18n';
import it from '@angular/common/locales/it';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NzInputModule} from "ng-zorro-antd/input";
import {NzLayoutModule} from "ng-zorro-antd/layout";
import {NzSpaceModule} from "ng-zorro-antd/space";
import {NzAvatarModule} from "ng-zorro-antd/avatar";
import {NzDropDownModule} from "ng-zorro-antd/dropdown";
import {NavBarComponent} from './shared/components/nav-bar/nav-bar.component';
import {NzImageModule} from "ng-zorro-antd/image";
import {NzDividerModule} from "ng-zorro-antd/divider";
import {NzGridModule} from "ng-zorro-antd/grid";

registerLocaleData(it);

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AuthorizationComponent,
    ErrorComponent,
    NoSanitizePipe,
    SrcPipe,
    ClickOutsideDirective,
    TruncatePipe,
    IconSizePipe,
    ModalDirective,
    DynamicComponentDirective,
    ImgFallbackDirective,
    TrackVisibilityDirective,
    IndexedPipe,
    NavBarComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    CommonModule,
    FormsModule,
    BrowserAnimationsModule,
    NzInputModule,
    NzLayoutModule,
    NzSpaceModule,
    NzAvatarModule,
    NzDropDownModule,
    NzImageModule,
    NgOptimizedImage,
    NzDividerModule,
    NzGridModule,
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
    },
    {provide: NZ_I18N, useValue: it_IT}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
