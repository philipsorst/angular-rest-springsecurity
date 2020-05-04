import {Component, OnInit, ViewChild} from '@angular/core';
import {MatSidenav} from "@angular/material/sidenav";
import {SidenavService} from "./sidenav/sidenav.service";
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {map} from "rxjs/operators";
import {Observable} from "rxjs";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {

    @ViewChild('sidenav', {static: true}) public sidenav: MatSidenav;

    public screenLarge$: Observable<boolean>;

    constructor(private sidenavService: SidenavService, private breakpointObserver: BreakpointObserver) {
    }

    private readonly largeBreakpoints = [
        Breakpoints.Medium,
        Breakpoints.Large,
        Breakpoints.XLarge
    ];

    /**
     * @override
     */
    public ngOnInit(): void {
        this.sidenavService.setSidenav(this.sidenav);
        this.screenLarge$ = this.breakpointObserver
            .observe(this.largeBreakpoints)
            .pipe(map(result => result.matches));
    }

    public conditionalSidenavToggle() {
        if (!this.breakpointObserver.isMatched(this.largeBreakpoints)) {
            this.sidenav.close();
        }
    }
}
