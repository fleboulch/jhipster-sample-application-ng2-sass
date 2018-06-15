import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationNg2SassSharedModule } from '../../shared';
import {
    EntrepriseService,
    EntreprisePopupService,
    EntrepriseComponent,
    EntrepriseDetailComponent,
    EntrepriseDialogComponent,
    EntreprisePopupComponent,
    EntrepriseDeletePopupComponent,
    EntrepriseDeleteDialogComponent,
    entrepriseRoute,
    entreprisePopupRoute,
    EntrepriseResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...entrepriseRoute,
    ...entreprisePopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationNg2SassSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        EntrepriseComponent,
        EntrepriseDetailComponent,
        EntrepriseDialogComponent,
        EntrepriseDeleteDialogComponent,
        EntreprisePopupComponent,
        EntrepriseDeletePopupComponent,
    ],
    entryComponents: [
        EntrepriseComponent,
        EntrepriseDialogComponent,
        EntreprisePopupComponent,
        EntrepriseDeleteDialogComponent,
        EntrepriseDeletePopupComponent,
    ],
    providers: [
        EntrepriseService,
        EntreprisePopupService,
        EntrepriseResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationNg2SassEntrepriseModule {}
