import {Injectable} from "@angular/core";
import {MatDrawerToggleResult, MatSidenav} from "@angular/material";

@Injectable({
    providedIn: 'root'
})
export class SidenavService
{
    private sidenav: MatSidenav;

    public setSidenav(sidenav: MatSidenav)
    {
        this.sidenav = sidenav;
    }

    public toggle(): Promise<MatDrawerToggleResult>
    {
        if (null == this.sidenav) {
            return Promise.resolve(null);
        }

        return this.sidenav.toggle();
    }

    public open(): Promise<MatDrawerToggleResult>
    {
        if (null == this.sidenav) {
            return Promise.resolve(null);
        }

        return this.sidenav.open();
    }

    public close(): Promise<MatDrawerToggleResult>
    {
        if (null == this.sidenav) {
            return Promise.resolve(null);
        }

        return this.sidenav.close();
    }
}
