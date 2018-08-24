import {User} from "../user/user";
import {Resource} from "hal-4-angular";

export class BlogPost extends Resource
{
  author: User;
  content: string;
  created: number;
  lastModified: number;
  slug: string;
  title: string;
    comments: Comment[];
}
