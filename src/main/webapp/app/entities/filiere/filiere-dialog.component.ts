import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Filiere } from './filiere.model';
import { FilierePopupService } from './filiere-popup.service';
import { FiliereService } from './filiere.service';
import { Diplome, DiplomeService } from '../diplome';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-filiere-dialog',
    templateUrl: './filiere-dialog.component.html'
})
export class FiliereDialogComponent implements OnInit {

    filiere: Filiere;
    isSaving: boolean;

    diplomes: Diplome[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private filiereService: FiliereService,
        private diplomeService: DiplomeService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.diplomeService.query()
            .subscribe((res: ResponseWrapper) => { this.diplomes = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.filiere.id !== undefined) {
            this.subscribeToSaveResponse(
                this.filiereService.update(this.filiere));
        } else {
            this.subscribeToSaveResponse(
                this.filiereService.create(this.filiere));
        }
    }

    private subscribeToSaveResponse(result: Observable<Filiere>) {
        result.subscribe((res: Filiere) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Filiere) {
        this.eventManager.broadcast({ name: 'filiereListModification', content: 'OK'});
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
}

@Component({
    selector: 'jhi-filiere-popup',
    template: ''
})
export class FilierePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private filierePopupService: FilierePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.filierePopupService
                    .open(FiliereDialogComponent as Component, params['id']);
            } else {
                this.filierePopupService
                    .open(FiliereDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
