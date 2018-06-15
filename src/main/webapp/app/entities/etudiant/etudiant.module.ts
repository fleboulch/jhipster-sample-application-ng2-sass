import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationNg2SassSharedModule } from '../../shared';
import {
    EtudiantService,
    EtudiantPopupService,
    EtudiantComponent,
    EtudiantDetailComponent,
    EtudiantDialogComponent,
    EtudiantPopupComponent,
    EtudiantDeletePopupComponent,
    EtudiantDeleteDialogComponent,
    etudiantRoute,
    etudiantPopupRoute,
    EtudiantResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...etudiantRoute,
    ...etudiantPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationNg2SassSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        EtudiantComponent,
        EtudiantDetailComponent,
        EtudiantDialogComponent,
        EtudiantDeleteDialogComponent,
        EtudiantPopupComponent,
        EtudiantDeletePopupComponent,
    ],
    entryComponents: [
        EtudiantComponent,
        EtudiantDialogComponent,
        EtudiantPopupComponent,
        EtudiantDeleteDialogComponent,
        EtudiantDeletePopupComponent,
    ],
    providers: [
        EtudiantService,
        EtudiantPopupService,
        EtudiantResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationNg2SassEtudiantModule {}
