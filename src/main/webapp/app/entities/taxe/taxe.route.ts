import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { TaxeComponent } from './taxe.component';
import { TaxeDetailComponent } from './taxe-detail.component';
import { TaxePopupComponent } from './taxe-dialog.component';
import { TaxeDeletePopupComponent } from './taxe-delete-dialog.component';

@Injectable()
export class TaxeResolvePagingParams implements Resolve<any> {

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

export const taxeRoute: Routes = [
    {
        path: 'taxe',
        component: TaxeComponent,
        resolve: {
            'pagingParams': TaxeResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Taxes'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'taxe/:id',
        component: TaxeDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Taxes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const taxePopupRoute: Routes = [
    {
        path: 'taxe-new',
        component: TaxePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Taxes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'taxe/:id/edit',
        component: TaxePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Taxes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'taxe/:id/delete',
        component: TaxeDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Taxes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
