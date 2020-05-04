import {Injectable} from '@angular/core';
import {RestResourceService} from "../rest/rest-resource.service";
import {HalApiService} from "../rest/hal-api.service";
import {User} from "./user";

@Injectable({
    providedIn: 'root'
})
export class UserService extends RestResourceService<User>
{
    constructor(protected halApiService: HalApiService)
    {
        super(halApiService, 'users');
    }
}
