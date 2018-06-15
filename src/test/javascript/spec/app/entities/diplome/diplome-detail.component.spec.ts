/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { JhipsterSampleApplicationNg2SassTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { DiplomeDetailComponent } from '../../../../../../main/webapp/app/entities/diplome/diplome-detail.component';
import { DiplomeService } from '../../../../../../main/webapp/app/entities/diplome/diplome.service';
import { Diplome } from '../../../../../../main/webapp/app/entities/diplome/diplome.model';

describe('Component Tests', () => {

    describe('Diplome Management Detail Component', () => {
        let comp: DiplomeDetailComponent;
        let fixture: ComponentFixture<DiplomeDetailComponent>;
        let service: DiplomeService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationNg2SassTestModule],
                declarations: [DiplomeDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    DiplomeService,
                    JhiEventManager
                ]
            }).overrideTemplate(DiplomeDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DiplomeDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DiplomeService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Diplome(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.diplome).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
