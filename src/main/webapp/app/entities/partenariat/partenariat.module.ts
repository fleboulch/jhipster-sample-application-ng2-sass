import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationNg2SassSharedModule } from '../../shared';
import {
    PartenariatService,
    PartenariatPopupService,
    PartenariatComponent,
    PartenariatDetailComponent,
    PartenariatDialogComponent,
    PartenariatPopupComponent,
    PartenariatDeletePopupComponent,
    PartenariatDeleteDialogComponent,
    partenariatRoute,
    partenariatPopupRoute,
    PartenariatResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...partenariatRoute,
    ...partenariatPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationNg2SassSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        PartenariatComponent,
        PartenariatDetailComponent,
        PartenariatDialogComponent,
        PartenariatDeleteDialogComponent,
        PartenariatPopupComponent,
        PartenariatDeletePopupComponent,
    ],
    entryComponents: [
        PartenariatComponent,
        PartenariatDialogComponent,
        PartenariatPopupComponent,
        PartenariatDeleteDialogComponent,
        PartenariatDeletePopupComponent,
    ],
    providers: [
        PartenariatService,
        PartenariatPopupService,
        PartenariatResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationNg2SassPartenariatModule {}
