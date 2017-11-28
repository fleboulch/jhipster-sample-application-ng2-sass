import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Professionnel } from './professionnel.model';
import { ProfessionnelPopupService } from './professionnel-popup.service';
import { ProfessionnelService } from './professionnel.service';

@Component({
    selector: 'jhi-professionnel-delete-dialog',
    templateUrl: './professionnel-delete-dialog.component.html'
})
export class ProfessionnelDeleteDialogComponent {

    professionnel: Professionnel;

    constructor(
        private professionnelService: ProfessionnelService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.professionnelService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'professionnelListModification',
                content: 'Deleted an professionnel'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-professionnel-delete-popup',
    template: ''
})
export class ProfessionnelDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private professionnelPopupService: ProfessionnelPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.professionnelPopupService
                .open(ProfessionnelDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
