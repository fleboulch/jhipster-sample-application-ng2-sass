import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ProfessionnelComponent } from './professionnel.component';
import { ProfessionnelDetailComponent } from './professionnel-detail.component';
import { ProfessionnelPopupComponent } from './professionnel-dialog.component';
import { ProfessionnelDeletePopupComponent } from './professionnel-delete-dialog.component';

@Injectable()
export class ProfessionnelResolvePagingParams implements Resolve<any> {

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

export const professionnelRoute: Routes = [
    {
        path: 'professionnel',
        component: ProfessionnelComponent,
        resolve: {
            'pagingParams': ProfessionnelResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Professionnels'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'professionnel/:id',
        component: ProfessionnelDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Professionnels'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const professionnelPopupRoute: Routes = [
    {
        path: 'professionnel-new',
        component: ProfessionnelPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Professionnels'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'professionnel/:id/edit',
        component: ProfessionnelPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Professionnels'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'professionnel/:id/delete',
        component: ProfessionnelDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Professionnels'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
