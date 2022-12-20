import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  form!: FormGroup;

  constructor(private fb: FormBuilder, private _http: HttpClient) {
    this.form = this.fb.group({
      username: new FormControl(null, [Validators.required]),
      password: new FormControl(null, [Validators.required])
    })
  }

  login() {
    const fr = new FormData();
    fr.append("username", this.form.value.username)
    fr.append("password", this.form.value.password)
    this._http.post("http://auth-server:9000/auth", fr).subscribe(value => console.log(value))
  }
}
