import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { EntrepriseComponent } from './entreprise.component';
import { EntrepriseDetailComponent } from './entreprise-detail.component';
import { EntreprisePopupComponent } from './entreprise-dialog.component';
import { EntrepriseDeletePopupComponent } from './entreprise-delete-dialog.component';

@Injectable()
export class EntrepriseResolvePagingParams implements Resolve<any> {

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

export const entrepriseRoute: Routes = [
    {
        path: 'entreprise',
        component: EntrepriseComponent,
        resolve: {
            'pagingParams': EntrepriseResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Entreprises'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'entreprise/:id',
        component: EntrepriseDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Entreprises'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const entreprisePopupRoute: Routes = [
    {
        path: 'entreprise-new',
        component: EntreprisePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Entreprises'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'entreprise/:id/edit',
        component: EntreprisePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Entreprises'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'entreprise/:id/delete',
        component: EntrepriseDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Entreprises'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
