import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Promotion } from './promotion.model';
import { PromotionPopupService } from './promotion-popup.service';
import { PromotionService } from './promotion.service';
import { Filiere, FiliereService } from '../filiere';
import { Etudiant, EtudiantService } from '../etudiant';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-promotion-dialog',
    templateUrl: './promotion-dialog.component.html'
})
export class PromotionDialogComponent implements OnInit {

    promotion: Promotion;
    isSaving: boolean;

    filieres: Filiere[];

    etudiants: Etudiant[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private promotionService: PromotionService,
        private filiereService: FiliereService,
        private etudiantService: EtudiantService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.filiereService.query()
            .subscribe((res: ResponseWrapper) => { this.filieres = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.etudiantService.query()
            .subscribe((res: ResponseWrapper) => { this.etudiants = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.promotion.id !== undefined) {
            this.subscribeToSaveResponse(
                this.promotionService.update(this.promotion));
        } else {
            this.subscribeToSaveResponse(
                this.promotionService.create(this.promotion));
        }
    }

    private subscribeToSaveResponse(result: Observable<Promotion>) {
        result.subscribe((res: Promotion) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Promotion) {
        this.eventManager.broadcast({ name: 'promotionListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackFiliereById(index: number, item: Filiere) {
        return item.id;
    }

    trackEtudiantById(index: number, item: Etudiant) {
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
    selector: 'jhi-promotion-popup',
    template: ''
})
export class PromotionPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private promotionPopupService: PromotionPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.promotionPopupService
                    .open(PromotionDialogComponent as Component, params['id']);
            } else {
                this.promotionPopupService
                    .open(PromotionDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
