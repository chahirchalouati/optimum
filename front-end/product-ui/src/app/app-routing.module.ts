import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthenticationComponent} from "./components/authentication/authentication.component";
import {LoginComponent} from "./components/login/login.component";

let routes: Routes;
routes = [
  {
    path: '',
    redirectTo: 'auth',
    pathMatch: "full"
  },
  {
    path: 'authorized',
    redirectTo: 'auth',
    pathMatch: "full"
  },
  {
    path: 'auth',
    component: AuthenticationComponent,
    pathMatch: "full"
  },
  {
    path: 'login',
    component: LoginComponent,
    pathMatch: "full"
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
