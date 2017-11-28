import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Diplome } from './diplome.model';
import { DiplomePopupService } from './diplome-popup.service';
import { DiplomeService } from './diplome.service';

@Component({
    selector: 'jhi-diplome-delete-dialog',
    templateUrl: './diplome-delete-dialog.component.html'
})
export class DiplomeDeleteDialogComponent {

    diplome: Diplome;

    constructor(
        private diplomeService: DiplomeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.diplomeService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'diplomeListModification',
                content: 'Deleted an diplome'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-diplome-delete-popup',
    template: ''
})
export class DiplomeDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private diplomePopupService: DiplomePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.diplomePopupService
                .open(DiplomeDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
