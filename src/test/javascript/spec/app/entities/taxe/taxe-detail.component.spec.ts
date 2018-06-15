/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { JhipsterSampleApplicationNg2SassTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { TaxeDetailComponent } from '../../../../../../main/webapp/app/entities/taxe/taxe-detail.component';
import { TaxeService } from '../../../../../../main/webapp/app/entities/taxe/taxe.service';
import { Taxe } from '../../../../../../main/webapp/app/entities/taxe/taxe.model';

describe('Component Tests', () => {

    describe('Taxe Management Detail Component', () => {
        let comp: TaxeDetailComponent;
        let fixture: ComponentFixture<TaxeDetailComponent>;
        let service: TaxeService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationNg2SassTestModule],
                declarations: [TaxeDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    TaxeService,
                    JhiEventManager
                ]
            }).overrideTemplate(TaxeDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TaxeDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TaxeService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Taxe(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.taxe).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
