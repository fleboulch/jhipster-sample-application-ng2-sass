import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Taxe } from './taxe.model';
import { TaxeService } from './taxe.service';

@Component({
    selector: 'jhi-taxe-detail',
    templateUrl: './taxe-detail.component.html'
})
export class TaxeDetailComponent implements OnInit, OnDestroy {

    taxe: Taxe;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private taxeService: TaxeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTaxes();
    }

    load(id) {
        this.taxeService.find(id).subscribe((taxe) => {
            this.taxe = taxe;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTaxes() {
        this.eventSubscriber = this.eventManager.subscribe(
            'taxeListModification',
            (response) => this.load(this.taxe.id)
        );
    }
}
