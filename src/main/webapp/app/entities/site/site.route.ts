import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { SiteComponent } from './site.component';
import { SiteDetailComponent } from './site-detail.component';
import { SitePopupComponent } from './site-dialog.component';
import { SiteDeletePopupComponent } from './site-delete-dialog.component';

@Injectable()
export class SiteResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const siteRoute: Routes = [
    {
        path: 'site',
        component: SiteComponent,
        resolve: {
            'pagingParams': SiteResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Sites'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'site/:id',
        component: SiteDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Sites'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sitePopupRoute: Routes = [
    {
        path: 'site-new',
        component: SitePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Sites'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'site/:id/edit',
        component: SitePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Sites'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'site/:id/delete',
        component: SiteDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Sites'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
