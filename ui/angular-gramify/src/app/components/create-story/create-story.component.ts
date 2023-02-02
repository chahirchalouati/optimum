import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import Profile from "../../shared/domain/Profile";

@Component({
  selector: 'app-create-story',
  templateUrl: './create-story.component.html',
  styleUrls: ['./create-story.component.scss']
})
export class CreateStoryComponent implements OnInit{
  @Input() profile$!: Observable<Profile>;

  ngOnInit(): void {
   this.profile$.subscribe(
     value => {
       console.log(value)
     }
   )

  }


}
