/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { JhipsterSampleApplicationNg2SassTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { GroupeDetailComponent } from '../../../../../../main/webapp/app/entities/groupe/groupe-detail.component';
import { GroupeService } from '../../../../../../main/webapp/app/entities/groupe/groupe.service';
import { Groupe } from '../../../../../../main/webapp/app/entities/groupe/groupe.model';

describe('Component Tests', () => {

    describe('Groupe Management Detail Component', () => {
        let comp: GroupeDetailComponent;
        let fixture: ComponentFixture<GroupeDetailComponent>;
        let service: GroupeService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationNg2SassTestModule],
                declarations: [GroupeDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    GroupeService,
                    JhiEventManager
                ]
            }).overrideTemplate(GroupeDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(GroupeDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GroupeService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Groupe(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.groupe).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
