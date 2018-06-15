import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Professionnel } from './professionnel.model';
import { ProfessionnelPopupService } from './professionnel-popup.service';
import { ProfessionnelService } from './professionnel.service';
import { Entreprise, EntrepriseService } from '../entreprise';
import { Diplome, DiplomeService } from '../diplome';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-professionnel-dialog',
    templateUrl: './professionnel-dialog.component.html'
})
export class ProfessionnelDialogComponent implements OnInit {

    professionnel: Professionnel;
    isSaving: boolean;

    entreprisecontacts: Entreprise[];

    diplomes: Diplome[];

    entreprises: Entreprise[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private professionnelService: ProfessionnelService,
        private entrepriseService: EntrepriseService,
        private diplomeService: DiplomeService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.entrepriseService
            .query({filter: 'contact(nom)-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.professionnel.entrepriseContactId) {
                    this.entreprisecontacts = res.json;
                } else {
                    this.entrepriseService
                        .find(this.professionnel.entrepriseContactId)
                        .subscribe((subRes: Entreprise) => {
                            this.entreprisecontacts = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.diplomeService.query()
            .subscribe((res: ResponseWrapper) => { this.diplomes = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.entrepriseService.query()
            .subscribe((res: ResponseWrapper) => { this.entreprises = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.professionnel.id !== undefined) {
            this.subscribeToSaveResponse(
                this.professionnelService.update(this.professionnel));
        } else {
            this.subscribeToSaveResponse(
                this.professionnelService.create(this.professionnel));
        }
    }

    private subscribeToSaveResponse(result: Observable<Professionnel>) {
        result.subscribe((res: Professionnel) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Professionnel) {
        this.eventManager.broadcast({ name: 'professionnelListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackEntrepriseById(index: number, item: Entreprise) {
        return item.id;
    }

    trackDiplomeById(index: number, item: Diplome) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

@Component({
    selector: 'jhi-professionnel-popup',
    template: ''
})
export class ProfessionnelPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private professionnelPopupService: ProfessionnelPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.professionnelPopupService
                    .open(ProfessionnelDialogComponent as Component, params['id']);
            } else {
                this.professionnelPopupService
                    .open(ProfessionnelDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
