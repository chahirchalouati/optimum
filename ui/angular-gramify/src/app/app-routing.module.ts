import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./pages/home/home.component";
import {AuthenticationGuard} from "./shared/gaurds/authentication.guard";
import {AuthorizationComponent} from "./pages/authorization/authorization.component";
import {ErrorComponent} from "./pages/error/error.component";

const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [AuthenticationGuard]
  },
  {
    path: '',
    component: AuthorizationComponent,
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
