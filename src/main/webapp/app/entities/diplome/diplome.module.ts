import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationNg2SassSharedModule } from '../../shared';
import {
    DiplomeService,
    DiplomePopupService,
    DiplomeComponent,
    DiplomeDetailComponent,
    DiplomeDialogComponent,
    DiplomePopupComponent,
    DiplomeDeletePopupComponent,
    DiplomeDeleteDialogComponent,
    diplomeRoute,
    diplomePopupRoute,
    DiplomeResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...diplomeRoute,
    ...diplomePopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationNg2SassSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        DiplomeComponent,
        DiplomeDetailComponent,
        DiplomeDialogComponent,
        DiplomeDeleteDialogComponent,
        DiplomePopupComponent,
        DiplomeDeletePopupComponent,
    ],
    entryComponents: [
        DiplomeComponent,
        DiplomeDialogComponent,
        DiplomePopupComponent,
        DiplomeDeleteDialogComponent,
        DiplomeDeletePopupComponent,
    ],
    providers: [
        DiplomeService,
        DiplomePopupService,
        DiplomeResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationNg2SassDiplomeModule {}
