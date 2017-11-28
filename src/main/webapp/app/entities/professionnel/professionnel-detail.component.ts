import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Professionnel } from './professionnel.model';
import { ProfessionnelService } from './professionnel.service';

@Component({
    selector: 'jhi-professionnel-detail',
    templateUrl: './professionnel-detail.component.html'
})
export class ProfessionnelDetailComponent implements OnInit, OnDestroy {

    professionnel: Professionnel;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private professionnelService: ProfessionnelService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInProfessionnels();
    }

    load(id) {
        this.professionnelService.find(id).subscribe((professionnel) => {
            this.professionnel = professionnel;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInProfessionnels() {
        this.eventSubscriber = this.eventManager.subscribe(
            'professionnelListModification',
            (response) => this.load(this.professionnel.id)
        );
    }
}
