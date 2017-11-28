import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { FiliereComponent } from './filiere.component';
import { FiliereDetailComponent } from './filiere-detail.component';
import { FilierePopupComponent } from './filiere-dialog.component';
import { FiliereDeletePopupComponent } from './filiere-delete-dialog.component';

@Injectable()
export class FiliereResolvePagingParams implements Resolve<any> {

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

export const filiereRoute: Routes = [
    {
        path: 'filiere',
        component: FiliereComponent,
        resolve: {
            'pagingParams': FiliereResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Filieres'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'filiere/:id',
        component: FiliereDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Filieres'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const filierePopupRoute: Routes = [
    {
        path: 'filiere-new',
        component: FilierePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Filieres'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'filiere/:id/edit',
        component: FilierePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Filieres'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'filiere/:id/delete',
        component: FiliereDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Filieres'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
