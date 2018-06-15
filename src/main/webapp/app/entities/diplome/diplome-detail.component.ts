import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Diplome } from './diplome.model';
import { DiplomeService } from './diplome.service';

@Component({
    selector: 'jhi-diplome-detail',
    templateUrl: './diplome-detail.component.html'
})
export class DiplomeDetailComponent implements OnInit, OnDestroy {

    diplome: Diplome;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private diplomeService: DiplomeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInDiplomes();
    }

    load(id) {
        this.diplomeService.find(id).subscribe((diplome) => {
            this.diplome = diplome;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInDiplomes() {
        this.eventSubscriber = this.eventManager.subscribe(
            'diplomeListModification',
            (response) => this.load(this.diplome.id)
        );
    }
}
