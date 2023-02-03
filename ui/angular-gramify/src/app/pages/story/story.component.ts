import {Component, Input, OnInit} from '@angular/core';
import {Observable, of} from "rxjs";
import Profile from "../../shared/domain/Profile";
import {ProfileService} from "../../services/profile.service";

@Component({
  selector: 'app-story',
  templateUrl: './story.component.html',
  styleUrls: ['./story.component.scss']
})
export class StoryComponent implements OnInit {
  @Input() profile$!: Observable<Profile>;

  constructor(private profileService: ProfileService) {
  }

  ngOnInit(): void {
    this.profile$ = of(this.profileService.getProfile())
  }
}
