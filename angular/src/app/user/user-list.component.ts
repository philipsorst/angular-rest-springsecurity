import {Component, OnDestroy, OnInit} from "@angular/core";
import {UserService} from "./user.service";
import {MatSnackBar} from "@angular/material";
import {User} from "./user";
import {CollectionResult} from "../rest/collection-result";

@Component({
    templateUrl: './user-list.component.html'
})
export class UserListComponent implements OnInit, OnDestroy
{
    public loading: boolean = false;

    public users: User[];

    constructor(private userService: UserService, private snackBar: MatSnackBar)
    {
    }

    /**
     * @override
     */
    public ngOnInit(): void
    {
        this.loading = true;
        this.userService.list(0, null, [{property: 'username', direction: 'ASC'}]).subscribe(
            (result: CollectionResult<User>) => {
                this.users = result.entries;
            },
            (error) => {
                this.snackBar.open('Could not load authors', 'OK');
            },
            () => {
                this.loading = false;
            }
        );
    }

    /**
     * @override
     */
    public ngOnDestroy(): void
    {
    }
}
