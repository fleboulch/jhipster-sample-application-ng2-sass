import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ConventionStageComponent } from './convention-stage.component';
import { ConventionStageDetailComponent } from './convention-stage-detail.component';
import { ConventionStagePopupComponent } from './convention-stage-dialog.component';
import { ConventionStageDeletePopupComponent } from './convention-stage-delete-dialog.component';

@Injectable()
export class ConventionStageResolvePagingParams implements Resolve<any> {

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

export const conventionStageRoute: Routes = [
    {
        path: 'convention-stage',
        component: ConventionStageComponent,
        resolve: {
            'pagingParams': ConventionStageResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ConventionStages'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'convention-stage/:id',
        component: ConventionStageDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ConventionStages'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const conventionStagePopupRoute: Routes = [
    {
        path: 'convention-stage-new',
        component: ConventionStagePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ConventionStages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'convention-stage/:id/edit',
        component: ConventionStagePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ConventionStages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'convention-stage/:id/delete',
        component: ConventionStageDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ConventionStages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
