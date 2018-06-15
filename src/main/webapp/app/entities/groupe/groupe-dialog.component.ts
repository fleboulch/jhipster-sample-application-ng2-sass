import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Groupe } from './groupe.model';
import { GroupePopupService } from './groupe-popup.service';
import { GroupeService } from './groupe.service';

@Component({
    selector: 'jhi-groupe-dialog',
    templateUrl: './groupe-dialog.component.html'
})
export class GroupeDialogComponent implements OnInit {

    groupe: Groupe;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private groupeService: GroupeService,
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
        if (this.groupe.id !== undefined) {
            this.subscribeToSaveResponse(
                this.groupeService.update(this.groupe));
        } else {
            this.subscribeToSaveResponse(
                this.groupeService.create(this.groupe));
        }
    }

    private subscribeToSaveResponse(result: Observable<Groupe>) {
        result.subscribe((res: Groupe) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Groupe) {
        this.eventManager.broadcast({ name: 'groupeListModification', content: 'OK'});
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
    selector: 'jhi-groupe-popup',
    template: ''
})
export class GroupePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private groupePopupService: GroupePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.groupePopupService
                    .open(GroupeDialogComponent as Component, params['id']);
            } else {
                this.groupePopupService
                    .open(GroupeDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
