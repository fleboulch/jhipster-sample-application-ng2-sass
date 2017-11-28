import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JhipsterSampleApplicationNg2SassConventionStageModule } from './convention-stage/convention-stage.module';
import { JhipsterSampleApplicationNg2SassTuteurModule } from './tuteur/tuteur.module';
import { JhipsterSampleApplicationNg2SassProfessionnelModule } from './professionnel/professionnel.module';
import { JhipsterSampleApplicationNg2SassEtudiantModule } from './etudiant/etudiant.module';
import { JhipsterSampleApplicationNg2SassEntrepriseModule } from './entreprise/entreprise.module';
import { JhipsterSampleApplicationNg2SassGroupeModule } from './groupe/groupe.module';
import { JhipsterSampleApplicationNg2SassSiteModule } from './site/site.module';
import { JhipsterSampleApplicationNg2SassDiplomeModule } from './diplome/diplome.module';
import { JhipsterSampleApplicationNg2SassPartenariatModule } from './partenariat/partenariat.module';
import { JhipsterSampleApplicationNg2SassFiliereModule } from './filiere/filiere.module';
import { JhipsterSampleApplicationNg2SassPromotionModule } from './promotion/promotion.module';
import { JhipsterSampleApplicationNg2SassTaxeModule } from './taxe/taxe.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        JhipsterSampleApplicationNg2SassConventionStageModule,
        JhipsterSampleApplicationNg2SassTuteurModule,
        JhipsterSampleApplicationNg2SassProfessionnelModule,
        JhipsterSampleApplicationNg2SassEtudiantModule,
        JhipsterSampleApplicationNg2SassEntrepriseModule,
        JhipsterSampleApplicationNg2SassGroupeModule,
        JhipsterSampleApplicationNg2SassSiteModule,
        JhipsterSampleApplicationNg2SassDiplomeModule,
        JhipsterSampleApplicationNg2SassPartenariatModule,
        JhipsterSampleApplicationNg2SassFiliereModule,
        JhipsterSampleApplicationNg2SassPromotionModule,
        JhipsterSampleApplicationNg2SassTaxeModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationNg2SassEntityModule {}
