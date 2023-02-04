import {Component} from '@angular/core';
import {ModalComponent} from "./modal.component";

@Component({
  selector: 'app-modal',
  templateUrl: './create-post-modal.component.html',
  styleUrls: ['./create-post-modal.component.scss']
})
export class CreatePostModal extends ModalComponent {
}
