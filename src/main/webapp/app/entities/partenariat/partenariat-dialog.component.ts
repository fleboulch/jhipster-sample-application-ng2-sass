import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Partenariat } from './partenariat.model';
import { PartenariatPopupService } from './partenariat-popup.service';
import { PartenariatService } from './partenariat.service';
import { Diplome, DiplomeService } from '../diplome';
import { Entreprise, EntrepriseService } from '../entreprise';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-partenariat-dialog',
    templateUrl: './partenariat-dialog.component.html'
})
export class PartenariatDialogComponent implements OnInit {

    partenariat: Partenariat;
    isSaving: boolean;

    diplomes: Diplome[];

    entreprises: Entreprise[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private partenariatService: PartenariatService,
        private diplomeService: DiplomeService,
        private entrepriseService: EntrepriseService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
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
        if (this.partenariat.id !== undefined) {
            this.subscribeToSaveResponse(
                this.partenariatService.update(this.partenariat));
        } else {
            this.subscribeToSaveResponse(
                this.partenariatService.create(this.partenariat));
        }
    }

    private subscribeToSaveResponse(result: Observable<Partenariat>) {
        result.subscribe((res: Partenariat) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Partenariat) {
        this.eventManager.broadcast({ name: 'partenariatListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackDiplomeById(index: number, item: Diplome) {
        return item.id;
    }

    trackEntrepriseById(index: number, item: Entreprise) {
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
    selector: 'jhi-partenariat-popup',
    template: ''
})
export class PartenariatPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private partenariatPopupService: PartenariatPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.partenariatPopupService
                    .open(PartenariatDialogComponent as Component, params['id']);
            } else {
                this.partenariatPopupService
                    .open(PartenariatDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
