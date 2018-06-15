import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Diplome } from './diplome.model';
import { DiplomePopupService } from './diplome-popup.service';
import { DiplomeService } from './diplome.service';
import { Partenariat, PartenariatService } from '../partenariat';
import { Professionnel, ProfessionnelService } from '../professionnel';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-diplome-dialog',
    templateUrl: './diplome-dialog.component.html'
})
export class DiplomeDialogComponent implements OnInit {

    diplome: Diplome;
    isSaving: boolean;

    partenariats: Partenariat[];

    professionnels: Professionnel[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private diplomeService: DiplomeService,
        private partenariatService: PartenariatService,
        private professionnelService: ProfessionnelService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.partenariatService.query()
            .subscribe((res: ResponseWrapper) => { this.partenariats = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.professionnelService.query()
            .subscribe((res: ResponseWrapper) => { this.professionnels = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.diplome.id !== undefined) {
            this.subscribeToSaveResponse(
                this.diplomeService.update(this.diplome));
        } else {
            this.subscribeToSaveResponse(
                this.diplomeService.create(this.diplome));
        }
    }

    private subscribeToSaveResponse(result: Observable<Diplome>) {
        result.subscribe((res: Diplome) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Diplome) {
        this.eventManager.broadcast({ name: 'diplomeListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackPartenariatById(index: number, item: Partenariat) {
        return item.id;
    }

    trackProfessionnelById(index: number, item: Professionnel) {
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
    selector: 'jhi-diplome-popup',
    template: ''
})
export class DiplomePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private diplomePopupService: DiplomePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.diplomePopupService
                    .open(DiplomeDialogComponent as Component, params['id']);
            } else {
                this.diplomePopupService
                    .open(DiplomeDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
