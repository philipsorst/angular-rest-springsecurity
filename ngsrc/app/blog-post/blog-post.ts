import {User} from "../user/user";
import {Comment} from "../comment/comment";

export class BlogPost
{
    author: User;
    content: string;
    created: number;
    updated: number;
    slug: string;
    title: string;
    comments: Comment[];
}
