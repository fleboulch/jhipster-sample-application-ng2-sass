import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Tuteur } from './tuteur.model';
import { TuteurPopupService } from './tuteur-popup.service';
import { TuteurService } from './tuteur.service';

@Component({
    selector: 'jhi-tuteur-delete-dialog',
    templateUrl: './tuteur-delete-dialog.component.html'
})
export class TuteurDeleteDialogComponent {

    tuteur: Tuteur;

    constructor(
        private tuteurService: TuteurService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tuteurService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'tuteurListModification',
                content: 'Deleted an tuteur'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tuteur-delete-popup',
    template: ''
})
export class TuteurDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tuteurPopupService: TuteurPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.tuteurPopupService
                .open(TuteurDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
