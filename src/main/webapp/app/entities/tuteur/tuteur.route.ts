import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { TuteurComponent } from './tuteur.component';
import { TuteurDetailComponent } from './tuteur-detail.component';
import { TuteurPopupComponent } from './tuteur-dialog.component';
import { TuteurDeletePopupComponent } from './tuteur-delete-dialog.component';

@Injectable()
export class TuteurResolvePagingParams implements Resolve<any> {

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

export const tuteurRoute: Routes = [
    {
        path: 'tuteur',
        component: TuteurComponent,
        resolve: {
            'pagingParams': TuteurResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tuteurs'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'tuteur/:id',
        component: TuteurDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tuteurs'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tuteurPopupRoute: Routes = [
    {
        path: 'tuteur-new',
        component: TuteurPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tuteurs'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tuteur/:id/edit',
        component: TuteurPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tuteurs'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tuteur/:id/delete',
        component: TuteurDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tuteurs'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
