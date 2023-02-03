import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CreatePostModal} from './create-post-modal.component';

describe('ModalComponent', () => {
  let component: CreatePostModal;
  let fixture: ComponentFixture<CreatePostModal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CreatePostModal]
    })
      .compileComponents();

    fixture = TestBed.createComponent(CreatePostModal);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
