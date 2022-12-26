import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./pages/home/home.component";
import {AuthenticationGuard} from "./shared/gaurds/authentication.guard";
import {AuthorizationComponent} from "./pages/authorization/authorization.component";

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivate: [AuthenticationGuard]
  },
  {
    path: 'authorized',
    component: AuthorizationComponent,
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
