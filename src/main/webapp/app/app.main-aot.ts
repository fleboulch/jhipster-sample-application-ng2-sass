import { platformBrowser } from '@angular/platform-browser';
import { ProdConfig } from './blocks/config/prod.config';
import { JhipsterSampleApplicationNg2SassAppModuleNgFactory } from '../../../../target/aot/src/main/webapp/app/app.module.ngfactory';

ProdConfig();

platformBrowser().bootstrapModuleFactory(JhipsterSampleApplicationNg2SassAppModuleNgFactory)
.then((success) => console.log(`Application started`))
.catch((err) => console.error(err));
