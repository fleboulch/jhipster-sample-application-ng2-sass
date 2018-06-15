import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Taxe } from './taxe.model';
import { TaxePopupService } from './taxe-popup.service';
import { TaxeService } from './taxe.service';

@Component({
    selector: 'jhi-taxe-delete-dialog',
    templateUrl: './taxe-delete-dialog.component.html'
})
export class TaxeDeleteDialogComponent {

    taxe: Taxe;

    constructor(
        private taxeService: TaxeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.taxeService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'taxeListModification',
                content: 'Deleted an taxe'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-taxe-delete-popup',
    template: ''
})
export class TaxeDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private taxePopupService: TaxePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.taxePopupService
                .open(TaxeDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
