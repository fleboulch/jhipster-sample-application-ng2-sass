import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationNg2SassSharedModule } from '../../shared';
import {
    TuteurService,
    TuteurPopupService,
    TuteurComponent,
    TuteurDetailComponent,
    TuteurDialogComponent,
    TuteurPopupComponent,
    TuteurDeletePopupComponent,
    TuteurDeleteDialogComponent,
    tuteurRoute,
    tuteurPopupRoute,
    TuteurResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...tuteurRoute,
    ...tuteurPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationNg2SassSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        TuteurComponent,
        TuteurDetailComponent,
        TuteurDialogComponent,
        TuteurDeleteDialogComponent,
        TuteurPopupComponent,
        TuteurDeletePopupComponent,
    ],
    entryComponents: [
        TuteurComponent,
        TuteurDialogComponent,
        TuteurPopupComponent,
        TuteurDeleteDialogComponent,
        TuteurDeletePopupComponent,
    ],
    providers: [
        TuteurService,
        TuteurPopupService,
        TuteurResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationNg2SassTuteurModule {}
