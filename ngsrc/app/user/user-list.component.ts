import {Component, OnInit} from "@angular/core";
import {UserService} from "./user.service";
import {User} from "./user";
import {MatSnackBar} from "@angular/material/snack-bar";
import {catchError, map} from "rxjs/operators";
import {Observable, throwError} from "rxjs";

@Component({
    templateUrl: './user-list.component.html'
})
export class UserListComponent implements OnInit {
    public users$: Observable<User[]>;

    constructor(private userService: UserService, private snackBar: MatSnackBar) {
    }

    /**
     * @override
     */
    public ngOnInit(): void {
        this.users$ = this.userService.list(0, null, [{property: 'username', direction: 'ASC'}])
            .pipe(
                catchError(error => {
                    this.snackBar.open('Could not load authors', 'OK');
                    return throwError(error);
                }),
                map(result => result.entries)
            );
    }
}
