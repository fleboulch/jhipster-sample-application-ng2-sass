import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationNg2SassSharedModule } from '../../shared';
import {
    FiliereService,
    FilierePopupService,
    FiliereComponent,
    FiliereDetailComponent,
    FiliereDialogComponent,
    FilierePopupComponent,
    FiliereDeletePopupComponent,
    FiliereDeleteDialogComponent,
    filiereRoute,
    filierePopupRoute,
    FiliereResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...filiereRoute,
    ...filierePopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationNg2SassSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        FiliereComponent,
        FiliereDetailComponent,
        FiliereDialogComponent,
        FiliereDeleteDialogComponent,
        FilierePopupComponent,
        FiliereDeletePopupComponent,
    ],
    entryComponents: [
        FiliereComponent,
        FiliereDialogComponent,
        FilierePopupComponent,
        FiliereDeleteDialogComponent,
        FiliereDeletePopupComponent,
    ],
    providers: [
        FiliereService,
        FilierePopupService,
        FiliereResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationNg2SassFiliereModule {}
