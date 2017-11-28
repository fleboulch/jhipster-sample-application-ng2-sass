import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Tuteur } from './tuteur.model';
import { TuteurPopupService } from './tuteur-popup.service';
import { TuteurService } from './tuteur.service';

@Component({
    selector: 'jhi-tuteur-dialog',
    templateUrl: './tuteur-dialog.component.html'
})
export class TuteurDialogComponent implements OnInit {

    tuteur: Tuteur;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private tuteurService: TuteurService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.tuteur.id !== undefined) {
            this.subscribeToSaveResponse(
                this.tuteurService.update(this.tuteur));
        } else {
            this.subscribeToSaveResponse(
                this.tuteurService.create(this.tuteur));
        }
    }

    private subscribeToSaveResponse(result: Observable<Tuteur>) {
        result.subscribe((res: Tuteur) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Tuteur) {
        this.eventManager.broadcast({ name: 'tuteurListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-tuteur-popup',
    template: ''
})
export class TuteurPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tuteurPopupService: TuteurPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.tuteurPopupService
                    .open(TuteurDialogComponent as Component, params['id']);
            } else {
                this.tuteurPopupService
                    .open(TuteurDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
