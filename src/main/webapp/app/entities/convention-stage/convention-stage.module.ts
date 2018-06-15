import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationNg2SassSharedModule } from '../../shared';
import {
    ConventionStageService,
    ConventionStagePopupService,
    ConventionStageComponent,
    ConventionStageDetailComponent,
    ConventionStageDialogComponent,
    ConventionStagePopupComponent,
    ConventionStageDeletePopupComponent,
    ConventionStageDeleteDialogComponent,
    conventionStageRoute,
    conventionStagePopupRoute,
    ConventionStageResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...conventionStageRoute,
    ...conventionStagePopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationNg2SassSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ConventionStageComponent,
        ConventionStageDetailComponent,
        ConventionStageDialogComponent,
        ConventionStageDeleteDialogComponent,
        ConventionStagePopupComponent,
        ConventionStageDeletePopupComponent,
    ],
    entryComponents: [
        ConventionStageComponent,
        ConventionStageDialogComponent,
        ConventionStagePopupComponent,
        ConventionStageDeleteDialogComponent,
        ConventionStageDeletePopupComponent,
    ],
    providers: [
        ConventionStageService,
        ConventionStagePopupService,
        ConventionStageResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationNg2SassConventionStageModule {}
