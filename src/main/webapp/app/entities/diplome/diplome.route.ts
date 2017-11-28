import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { DiplomeComponent } from './diplome.component';
import { DiplomeDetailComponent } from './diplome-detail.component';
import { DiplomePopupComponent } from './diplome-dialog.component';
import { DiplomeDeletePopupComponent } from './diplome-delete-dialog.component';

@Injectable()
export class DiplomeResolvePagingParams implements Resolve<any> {

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

export const diplomeRoute: Routes = [
    {
        path: 'diplome',
        component: DiplomeComponent,
        resolve: {
            'pagingParams': DiplomeResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Diplomes'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'diplome/:id',
        component: DiplomeDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Diplomes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const diplomePopupRoute: Routes = [
    {
        path: 'diplome-new',
        component: DiplomePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Diplomes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'diplome/:id/edit',
        component: DiplomePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Diplomes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'diplome/:id/delete',
        component: DiplomeDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Diplomes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
