import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { PromotionComponent } from './promotion.component';
import { PromotionDetailComponent } from './promotion-detail.component';
import { PromotionPopupComponent } from './promotion-dialog.component';
import { PromotionDeletePopupComponent } from './promotion-delete-dialog.component';

@Injectable()
export class PromotionResolvePagingParams implements Resolve<any> {

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

export const promotionRoute: Routes = [
    {
        path: 'promotion',
        component: PromotionComponent,
        resolve: {
            'pagingParams': PromotionResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Promotions'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'promotion/:id',
        component: PromotionDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Promotions'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const promotionPopupRoute: Routes = [
    {
        path: 'promotion-new',
        component: PromotionPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Promotions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'promotion/:id/edit',
        component: PromotionPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Promotions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'promotion/:id/delete',
        component: PromotionDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Promotions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
