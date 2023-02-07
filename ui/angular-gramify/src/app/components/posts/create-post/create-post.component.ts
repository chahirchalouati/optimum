import {Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {AuthenticationService} from "../../../services/authentication.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Observable} from "rxjs";
import Profile from "../../../shared/domain/Profile";
import {environment} from "../../../../environment/environment";
import {CreatePostModal} from "../../common/modal/create-post-modal.component";

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss']
})
export class CreatePostComponent implements OnInit {
  @Input() profile$!: Observable<Profile>;
  @Input() placeholder: string = '';
  createPostForm!: FormGroup;
  modal: any = CreatePostModal;

  constructor(private authenticationService: AuthenticationService,
              private viewContainerRef: ViewContainerRef,
              private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.createPostForm = this.fb.group(
      {
        content: new FormControl(null, Validators.required),
        files: new FormControl([]),
      }
    )

  }

  getPlaceHolder(username: string | undefined) {
    return environment.createPostPlaceholder.replace("%s", username as string);
  }

  openModal(modal: CreatePostModal) {
    modal.show = true;
  }
}
