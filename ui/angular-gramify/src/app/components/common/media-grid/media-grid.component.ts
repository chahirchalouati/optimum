import {Component, Input, OnInit} from '@angular/core';
import {Attachment, Pair} from "../../../shared/domain/Post";

@Component({
  selector: 'app-media-grid',
  templateUrl: './media-grid.component.html',
  styleUrls: ['./media-grid.component.scss']
})
export class MediaGridComponent implements OnInit {
  @Input() attachments: Attachment[] = [];
  private keys: Array<string> = [];
  isControlsActive: boolean = false;
  autoPlay: boolean = false;

  ngOnInit(): void {
    this.keys = Object.keys(this.attachments[0].additionalData)
  }

  isVideo(contentType: string) {
    return !!contentType && contentType.startsWith("video");
  }

  isImage(contentType: string) {
    return !!contentType && contentType.startsWith("image");
  }


  getImage(attachment: Attachment): Pair {
    const randomIndex = Math.floor(Math.random() * this.keys.length);
    return attachment.additionalData[this.keys[randomIndex]];
  }

  setVisibility($event: boolean, videoElement: HTMLVideoElement) {
    if ($event) {
      videoElement.play();
    } else {
      videoElement.pause();
    }
  }
}
