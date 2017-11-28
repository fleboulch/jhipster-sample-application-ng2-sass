import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationNg2SassSharedModule } from '../../shared';
import {
    SiteService,
    SitePopupService,
    SiteComponent,
    SiteDetailComponent,
    SiteDialogComponent,
    SitePopupComponent,
    SiteDeletePopupComponent,
    SiteDeleteDialogComponent,
    siteRoute,
    sitePopupRoute,
    SiteResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...siteRoute,
    ...sitePopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationNg2SassSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        SiteComponent,
        SiteDetailComponent,
        SiteDialogComponent,
        SiteDeleteDialogComponent,
        SitePopupComponent,
        SiteDeletePopupComponent,
    ],
    entryComponents: [
        SiteComponent,
        SiteDialogComponent,
        SitePopupComponent,
        SiteDeleteDialogComponent,
        SiteDeletePopupComponent,
    ],
    providers: [
        SiteService,
        SitePopupService,
        SiteResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationNg2SassSiteModule {}
