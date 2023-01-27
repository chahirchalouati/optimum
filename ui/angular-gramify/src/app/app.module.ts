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
import {NavbarComponent} from './components/navigation/navbar/navbar.component';
import {SidebarComponent} from './components/navigation/sidebar/sidebar.component';
import {IconComponent} from './components/common/icon/icon.component';
import {SearchBarComponent} from './components/navigation/search-bar/search-bar.component';
import {DropDownBoxComponent} from './components/common/drop-down-box/drop-down-box.component';
import {AvatarComponent} from './components/common/avatar/avatar.component';
import {SrcPipe} from "./shared/pipes/src.pipe";
import {ClickOutsideDirective} from './shared/directives/click-outside.directive';
import {PostCardComponent} from './components/posts/post-card/post-card.component';
import {TruncatePipe} from './shared/pipes/truncate.pipe';
import {IconSizePipe} from './shared/pipes/icon-size.pipe';
import {CreatePostComponent} from './components/posts/create-post/create-post.component';
import {DividerComponent} from './components/divider/divider.component';
import {ButtonComponent} from './components/common/button/button.component';
import {CardStoryComponent} from './components/card-story/card-story.component';
import {CreateStoryComponent} from './components/create-story/create-story.component';
import {ModalComponent} from './components/common/modal/modal.component';

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
    TruncatePipe,
    IconSizePipe,
    CreatePostComponent,
    DividerComponent,
    ButtonComponent,
    CardStoryComponent,
    CreateStoryComponent,
    ModalComponent
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
