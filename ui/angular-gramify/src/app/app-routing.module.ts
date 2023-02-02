import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./pages/home/home.component";
import {AuthenticationGuard} from "./shared/gaurds/authentication.guard";
import {AuthorizationComponent} from "./pages/authorization/authorization.component";
import {ErrorComponent} from "./pages/error/error.component";
import {StoryComponent} from "./pages/story/story.component";

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivate: [AuthenticationGuard]
  },
  {
    path: 'story',
    component: StoryComponent,
    canActivate: [AuthenticationGuard]
  },

  {
    path: 'authorized',
    component: AuthorizationComponent,
  },
  {
    path: 'error',
    component: ErrorComponent,
  }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
