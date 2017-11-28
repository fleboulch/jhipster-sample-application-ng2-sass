/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { JhipsterSampleApplicationNg2SassTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { PromotionDetailComponent } from '../../../../../../main/webapp/app/entities/promotion/promotion-detail.component';
import { PromotionService } from '../../../../../../main/webapp/app/entities/promotion/promotion.service';
import { Promotion } from '../../../../../../main/webapp/app/entities/promotion/promotion.model';

describe('Component Tests', () => {

    describe('Promotion Management Detail Component', () => {
        let comp: PromotionDetailComponent;
        let fixture: ComponentFixture<PromotionDetailComponent>;
        let service: PromotionService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationNg2SassTestModule],
                declarations: [PromotionDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    PromotionService,
                    JhiEventManager
                ]
            }).overrideTemplate(PromotionDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PromotionDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PromotionService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Promotion(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.promotion).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
