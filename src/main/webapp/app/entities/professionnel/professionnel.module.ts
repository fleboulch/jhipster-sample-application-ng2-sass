import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationNg2SassSharedModule } from '../../shared';
import {
    ProfessionnelService,
    ProfessionnelPopupService,
    ProfessionnelComponent,
    ProfessionnelDetailComponent,
    ProfessionnelDialogComponent,
    ProfessionnelPopupComponent,
    ProfessionnelDeletePopupComponent,
    ProfessionnelDeleteDialogComponent,
    professionnelRoute,
    professionnelPopupRoute,
    ProfessionnelResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...professionnelRoute,
    ...professionnelPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationNg2SassSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ProfessionnelComponent,
        ProfessionnelDetailComponent,
        ProfessionnelDialogComponent,
        ProfessionnelDeleteDialogComponent,
        ProfessionnelPopupComponent,
        ProfessionnelDeletePopupComponent,
    ],
    entryComponents: [
        ProfessionnelComponent,
        ProfessionnelDialogComponent,
        ProfessionnelPopupComponent,
        ProfessionnelDeleteDialogComponent,
        ProfessionnelDeletePopupComponent,
    ],
    providers: [
        ProfessionnelService,
        ProfessionnelPopupService,
        ProfessionnelResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationNg2SassProfessionnelModule {}
