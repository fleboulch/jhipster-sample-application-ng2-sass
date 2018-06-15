import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Tuteur } from './tuteur.model';
import { TuteurService } from './tuteur.service';

@Component({
    selector: 'jhi-tuteur-detail',
    templateUrl: './tuteur-detail.component.html'
})
export class TuteurDetailComponent implements OnInit, OnDestroy {

    tuteur: Tuteur;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private tuteurService: TuteurService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTuteurs();
    }

    load(id) {
        this.tuteurService.find(id).subscribe((tuteur) => {
            this.tuteur = tuteur;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTuteurs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'tuteurListModification',
            (response) => this.load(this.tuteur.id)
        );
    }
}
