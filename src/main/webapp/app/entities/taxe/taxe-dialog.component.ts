import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Taxe } from './taxe.model';
import { TaxePopupService } from './taxe-popup.service';
import { TaxeService } from './taxe.service';
import { Entreprise, EntrepriseService } from '../entreprise';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-taxe-dialog',
    templateUrl: './taxe-dialog.component.html'
})
export class TaxeDialogComponent implements OnInit {

    taxe: Taxe;
    isSaving: boolean;

    entreprises: Entreprise[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private taxeService: TaxeService,
        private entrepriseService: EntrepriseService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.entrepriseService.query()
            .subscribe((res: ResponseWrapper) => { this.entreprises = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.taxe.id !== undefined) {
            this.subscribeToSaveResponse(
                this.taxeService.update(this.taxe));
        } else {
            this.subscribeToSaveResponse(
                this.taxeService.create(this.taxe));
        }
    }

    private subscribeToSaveResponse(result: Observable<Taxe>) {
        result.subscribe((res: Taxe) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Taxe) {
        this.eventManager.broadcast({ name: 'taxeListModification', content: 'OK'});
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
}

@Component({
    selector: 'jhi-taxe-popup',
    template: ''
})
export class TaxePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private taxePopupService: TaxePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.taxePopupService
                    .open(TaxeDialogComponent as Component, params['id']);
            } else {
                this.taxePopupService
                    .open(TaxeDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
