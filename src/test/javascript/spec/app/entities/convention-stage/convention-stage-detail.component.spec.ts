/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { JhipsterSampleApplicationNg2SassTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ConventionStageDetailComponent } from '../../../../../../main/webapp/app/entities/convention-stage/convention-stage-detail.component';
import { ConventionStageService } from '../../../../../../main/webapp/app/entities/convention-stage/convention-stage.service';
import { ConventionStage } from '../../../../../../main/webapp/app/entities/convention-stage/convention-stage.model';

describe('Component Tests', () => {

    describe('ConventionStage Management Detail Component', () => {
        let comp: ConventionStageDetailComponent;
        let fixture: ComponentFixture<ConventionStageDetailComponent>;
        let service: ConventionStageService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationNg2SassTestModule],
                declarations: [ConventionStageDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ConventionStageService,
                    JhiEventManager
                ]
            }).overrideTemplate(ConventionStageDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConventionStageDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConventionStageService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new ConventionStage(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.conventionStage).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
