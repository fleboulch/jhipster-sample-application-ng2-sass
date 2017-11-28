import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationNg2SassSharedModule } from '../../shared';
import {
    GroupeService,
    GroupePopupService,
    GroupeComponent,
    GroupeDetailComponent,
    GroupeDialogComponent,
    GroupePopupComponent,
    GroupeDeletePopupComponent,
    GroupeDeleteDialogComponent,
    groupeRoute,
    groupePopupRoute,
    GroupeResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...groupeRoute,
    ...groupePopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationNg2SassSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        GroupeComponent,
        GroupeDetailComponent,
        GroupeDialogComponent,
        GroupeDeleteDialogComponent,
        GroupePopupComponent,
        GroupeDeletePopupComponent,
    ],
    entryComponents: [
        GroupeComponent,
        GroupeDialogComponent,
        GroupePopupComponent,
        GroupeDeleteDialogComponent,
        GroupeDeletePopupComponent,
    ],
    providers: [
        GroupeService,
        GroupePopupService,
        GroupeResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationNg2SassGroupeModule {}
