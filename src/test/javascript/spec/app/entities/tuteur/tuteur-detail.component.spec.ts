/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { JhipsterSampleApplicationNg2SassTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { TuteurDetailComponent } from '../../../../../../main/webapp/app/entities/tuteur/tuteur-detail.component';
import { TuteurService } from '../../../../../../main/webapp/app/entities/tuteur/tuteur.service';
import { Tuteur } from '../../../../../../main/webapp/app/entities/tuteur/tuteur.model';

describe('Component Tests', () => {

    describe('Tuteur Management Detail Component', () => {
        let comp: TuteurDetailComponent;
        let fixture: ComponentFixture<TuteurDetailComponent>;
        let service: TuteurService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationNg2SassTestModule],
                declarations: [TuteurDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    TuteurService,
                    JhiEventManager
                ]
            }).overrideTemplate(TuteurDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TuteurDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TuteurService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Tuteur(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.tuteur).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
