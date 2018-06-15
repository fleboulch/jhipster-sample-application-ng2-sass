/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { JhipsterSampleApplicationNg2SassTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { PartenariatDetailComponent } from '../../../../../../main/webapp/app/entities/partenariat/partenariat-detail.component';
import { PartenariatService } from '../../../../../../main/webapp/app/entities/partenariat/partenariat.service';
import { Partenariat } from '../../../../../../main/webapp/app/entities/partenariat/partenariat.model';

describe('Component Tests', () => {

    describe('Partenariat Management Detail Component', () => {
        let comp: PartenariatDetailComponent;
        let fixture: ComponentFixture<PartenariatDetailComponent>;
        let service: PartenariatService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationNg2SassTestModule],
                declarations: [PartenariatDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    PartenariatService,
                    JhiEventManager
                ]
            }).overrideTemplate(PartenariatDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PartenariatDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PartenariatService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Partenariat(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.partenariat).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
