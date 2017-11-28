import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { ConventionStage } from './convention-stage.model';
import { ConventionStageService } from './convention-stage.service';

@Component({
    selector: 'jhi-convention-stage-detail',
    templateUrl: './convention-stage-detail.component.html'
})
export class ConventionStageDetailComponent implements OnInit, OnDestroy {

    conventionStage: ConventionStage;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private conventionStageService: ConventionStageService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInConventionStages();
    }

    load(id) {
        this.conventionStageService.find(id).subscribe((conventionStage) => {
            this.conventionStage = conventionStage;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInConventionStages() {
        this.eventSubscriber = this.eventManager.subscribe(
            'conventionStageListModification',
            (response) => this.load(this.conventionStage.id)
        );
    }
}
