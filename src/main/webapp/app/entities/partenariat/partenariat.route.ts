import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { PartenariatComponent } from './partenariat.component';
import { PartenariatDetailComponent } from './partenariat-detail.component';
import { PartenariatPopupComponent } from './partenariat-dialog.component';
import { PartenariatDeletePopupComponent } from './partenariat-delete-dialog.component';

@Injectable()
export class PartenariatResolvePagingParams implements Resolve<any> {

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

export const partenariatRoute: Routes = [
    {
        path: 'partenariat',
        component: PartenariatComponent,
        resolve: {
            'pagingParams': PartenariatResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Partenariats'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'partenariat/:id',
        component: PartenariatDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Partenariats'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const partenariatPopupRoute: Routes = [
    {
        path: 'partenariat-new',
        component: PartenariatPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Partenariats'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'partenariat/:id/edit',
        component: PartenariatPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Partenariats'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'partenariat/:id/delete',
        component: PartenariatDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Partenariats'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
