import {Component, Input, OnInit, TemplateRef, ViewChild, ViewContainerRef} from '@angular/core';
import {AuthenticationService} from "../../../services/authentication.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Observable} from "rxjs";
import Profile from "../../../shared/domain/Profile";
import {environment} from "../../../../environment/environment";
import {ModalComponent} from "../../common/modal/modal.component";

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss']
})
export class CreatePostComponent implements OnInit {
  @ViewChild('modalContent', {static: true}) modalContent!: TemplateRef<any>;
  @Input() profile$!: Observable<Profile>;
  @Input() placeholder: string = '';
  createPostForm!: FormGroup;
  modal: any = ModalComponent;

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

  getPlaceholder(username: string | undefined) {
    return environment.createPostPlaceholder.replace("%s", username as string);
  }

  getCreatePostModal() {
    const modalComponent = new ModalComponent();
    modalComponent.show = true;
    modalComponent.title = 'create post'
    return modalComponent;
  }

  openModal(modal: ModalComponent) {
    modal.show = true;
    modal.title = 'Create post'
  }
}
