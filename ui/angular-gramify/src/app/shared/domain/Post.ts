
import {Selectable} from "./Pageable";

export default interface Post extends Selectable {
  id: string
  content: string
  creator: Creator
  access: string
  videos: any[]
  images: Image[]
  tags: any[]
  likes: any[]
  commentCount: number
  shareCount: number
  likesCount: number
  disLikesCount: number
  actions: Actions
  createDate: string
  lastModifiedDate: string
}

export interface Image {
  index: number
  id: string
  parent: string
  url: string
  contentType: string
  imageSize: ImageSize
  processedImages: Image[]
}

export interface ImageSize {
  width: number
  height: number
  name: string
  label: string
}


export interface Creator {
  username: string
  avatar: string
  backgroundImage: string
  user: User
}

export interface User {
  firstName: string
  lastName: string
  username: string
  email: string
  password: any
  roles: any[]
  gender: string
  avatar: any
  isAccountNonExpired: boolean
  isEnabled: boolean
  isCredentialsNonExpired: boolean
  isAccountNonLocked: boolean
  enabled: boolean
  accountNonExpired: boolean
  credentialsNonExpired: boolean
  accountNonLocked: boolean
}

export interface Image {
  index: number
  id: string
  parent: string
  url: string
  contentType: string
  imageSize: ImageSize
  processedImages: Image[]
}


export interface ImageSize {
  width: number
  height: number
  name: string
  label: string
}

export interface Actions {
  hide: boolean
  edit: boolean
  like: boolean
  dislike: boolean
  update: boolean
  share: boolean
  delete: boolean
}
