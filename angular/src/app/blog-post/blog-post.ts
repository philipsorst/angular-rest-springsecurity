import {User} from "../user/user";
import {Comment} from "../comment/comment";

export class BlogPost
{
    author: User;
    content: string;
    created: number;
    lastModified: number;
    slug: string;
    title: string;
    comments: Comment[];
}
