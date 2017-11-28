import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ConventionStage } from './convention-stage.model';
import { ConventionStagePopupService } from './convention-stage-popup.service';
import { ConventionStageService } from './convention-stage.service';

@Component({
    selector: 'jhi-convention-stage-delete-dialog',
    templateUrl: './convention-stage-delete-dialog.component.html'
})
export class ConventionStageDeleteDialogComponent {

    conventionStage: ConventionStage;

    constructor(
        private conventionStageService: ConventionStageService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.conventionStageService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'conventionStageListModification',
                content: 'Deleted an conventionStage'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-convention-stage-delete-popup',
    template: ''
})
export class ConventionStageDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private conventionStagePopupService: ConventionStagePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.conventionStagePopupService
                .open(ConventionStageDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
