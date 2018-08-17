import {User} from "../user/user";
import {Resource} from "angular4-hal";

export class BlogPost extends Resource
{
  author: User;
  content: string;
  created: number;
  lastModified: number;
  slug: string;
  title: string;
}
