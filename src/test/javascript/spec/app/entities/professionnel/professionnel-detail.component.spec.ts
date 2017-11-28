/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { JhipsterSampleApplicationNg2SassTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ProfessionnelDetailComponent } from '../../../../../../main/webapp/app/entities/professionnel/professionnel-detail.component';
import { ProfessionnelService } from '../../../../../../main/webapp/app/entities/professionnel/professionnel.service';
import { Professionnel } from '../../../../../../main/webapp/app/entities/professionnel/professionnel.model';

describe('Component Tests', () => {

    describe('Professionnel Management Detail Component', () => {
        let comp: ProfessionnelDetailComponent;
        let fixture: ComponentFixture<ProfessionnelDetailComponent>;
        let service: ProfessionnelService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationNg2SassTestModule],
                declarations: [ProfessionnelDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ProfessionnelService,
                    JhiEventManager
                ]
            }).overrideTemplate(ProfessionnelDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProfessionnelDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProfessionnelService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Professionnel(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.professionnel).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
