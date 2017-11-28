import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Partenariat } from './partenariat.model';
import { PartenariatPopupService } from './partenariat-popup.service';
import { PartenariatService } from './partenariat.service';

@Component({
    selector: 'jhi-partenariat-delete-dialog',
    templateUrl: './partenariat-delete-dialog.component.html'
})
export class PartenariatDeleteDialogComponent {

    partenariat: Partenariat;

    constructor(
        private partenariatService: PartenariatService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.partenariatService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'partenariatListModification',
                content: 'Deleted an partenariat'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-partenariat-delete-popup',
    template: ''
})
export class PartenariatDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private partenariatPopupService: PartenariatPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.partenariatPopupService
                .open(PartenariatDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
