/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { JhipsterSampleApplicationNg2SassTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { EntrepriseDetailComponent } from '../../../../../../main/webapp/app/entities/entreprise/entreprise-detail.component';
import { EntrepriseService } from '../../../../../../main/webapp/app/entities/entreprise/entreprise.service';
import { Entreprise } from '../../../../../../main/webapp/app/entities/entreprise/entreprise.model';

describe('Component Tests', () => {

    describe('Entreprise Management Detail Component', () => {
        let comp: EntrepriseDetailComponent;
        let fixture: ComponentFixture<EntrepriseDetailComponent>;
        let service: EntrepriseService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationNg2SassTestModule],
                declarations: [EntrepriseDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    EntrepriseService,
                    JhiEventManager
                ]
            }).overrideTemplate(EntrepriseDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(EntrepriseDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntrepriseService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Entreprise(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.entreprise).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
