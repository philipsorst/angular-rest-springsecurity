import {Component, OnInit, ViewChild} from '@angular/core';
import {MatSidenav} from "@angular/material";
import {BreakpointObserver} from "@angular/cdk/layout";
import {SidenavService} from "./sidenav/sidenav.service";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
})
export class AppComponent implements OnInit
{
    @ViewChild('sidenav')
    public sidenav: MatSidenav;

    public small: boolean = false;

    public medium: boolean = false;

    public large: boolean = false;

    constructor(private sidenavService: SidenavService, private breakpointObserver: BreakpointObserver)
    {
        breakpointObserver.observe('(min-width: 600px)').subscribe(result => {
            this.small = result.matches;
        });

        breakpointObserver.observe('(min-width: 960px)').subscribe(result => {
            this.medium = result.matches;
        });

        breakpointObserver.observe('(min-width: 1280px)').subscribe(result => {
            this.large = result.matches;
        });
    }

    /**
     * @override
     */
    public ngOnInit()
    {
        this.sidenavService.setSidenav(this.sidenav);
    }

    public conditionalSidenavToggle()
    {
        if (!this.medium) {
            this.sidenav.close();
        }
    }
}
