import './vendor.ts';

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Ng2Webstorage } from 'ng2-webstorage';

import { JhipsterSampleApplicationNg2SassSharedModule, UserRouteAccessService } from './shared';
import { JhipsterSampleApplicationNg2SassHomeModule } from './home/home.module';
import { JhipsterSampleApplicationNg2SassAdminModule } from './admin/admin.module';
import { JhipsterSampleApplicationNg2SassAccountModule } from './account/account.module';
import { JhipsterSampleApplicationNg2SassEntityModule } from './entities/entity.module';

import { customHttpProvider } from './blocks/interceptor/http.provider';
import { PaginationConfig } from './blocks/config/uib-pagination.config';

// jhipster-needle-angular-add-module-import JHipster will add new module here

import {
    JhiMainComponent,
    LayoutRoutingModule,
    NavbarComponent,
    FooterComponent,
    ProfileService,
    PageRibbonComponent,
    ErrorComponent
} from './layouts';

@NgModule({
    imports: [
        BrowserModule,
        LayoutRoutingModule,
        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-'}),
        JhipsterSampleApplicationNg2SassSharedModule,
        JhipsterSampleApplicationNg2SassHomeModule,
        JhipsterSampleApplicationNg2SassAdminModule,
        JhipsterSampleApplicationNg2SassAccountModule,
        JhipsterSampleApplicationNg2SassEntityModule,
        // jhipster-needle-angular-add-module JHipster will add new module here
    ],
    declarations: [
        JhiMainComponent,
        NavbarComponent,
        ErrorComponent,
        PageRibbonComponent,
        FooterComponent
    ],
    providers: [
        ProfileService,
        customHttpProvider(),
        PaginationConfig,
        UserRouteAccessService
    ],
    bootstrap: [ JhiMainComponent ]
})
export class JhipsterSampleApplicationNg2SassAppModule {}
