import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationNg2SassSharedModule } from '../../shared';
import {
    TaxeService,
    TaxePopupService,
    TaxeComponent,
    TaxeDetailComponent,
    TaxeDialogComponent,
    TaxePopupComponent,
    TaxeDeletePopupComponent,
    TaxeDeleteDialogComponent,
    taxeRoute,
    taxePopupRoute,
    TaxeResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...taxeRoute,
    ...taxePopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationNg2SassSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        TaxeComponent,
        TaxeDetailComponent,
        TaxeDialogComponent,
        TaxeDeleteDialogComponent,
        TaxePopupComponent,
        TaxeDeletePopupComponent,
    ],
    entryComponents: [
        TaxeComponent,
        TaxeDialogComponent,
        TaxePopupComponent,
        TaxeDeleteDialogComponent,
        TaxeDeletePopupComponent,
    ],
    providers: [
        TaxeService,
        TaxePopupService,
        TaxeResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationNg2SassTaxeModule {}
