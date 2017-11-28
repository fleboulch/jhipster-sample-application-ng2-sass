import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Partenariat } from './partenariat.model';
import { PartenariatService } from './partenariat.service';

@Component({
    selector: 'jhi-partenariat-detail',
    templateUrl: './partenariat-detail.component.html'
})
export class PartenariatDetailComponent implements OnInit, OnDestroy {

    partenariat: Partenariat;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private partenariatService: PartenariatService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPartenariats();
    }

    load(id) {
        this.partenariatService.find(id).subscribe((partenariat) => {
            this.partenariat = partenariat;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInPartenariats() {
        this.eventSubscriber = this.eventManager.subscribe(
            'partenariatListModification',
            (response) => this.load(this.partenariat.id)
        );
    }
}
