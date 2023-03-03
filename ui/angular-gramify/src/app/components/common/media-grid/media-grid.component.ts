import {Component, Input, OnInit} from '@angular/core';
import {Attachment} from "../../../shared/domain/Attachment";

@Component({
  selector: 'app-media-grid',
  templateUrl: './media-grid.component.html',
  styleUrls: ['./media-grid.component.scss']
})
export class MediaGridComponent implements OnInit {
  @Input() attachments: Attachment[] = [];
  @Input() maxPostElement: number = 4;
  isControlsActive: boolean = true;
  next: string = '';

  ngOnInit(): void {
  }

  isVideo(contentType: string) {
    return !!contentType && contentType.startsWith("video");
  }

  isImage(contentType: string) {
    return !!contentType && contentType.startsWith("image");
  }

  autoPlayVideo($event: boolean, videoElement: HTMLVideoElement) {
    if ($event) {
      videoElement.play()
    } else {
      videoElement.pause();
    }
  }
}
