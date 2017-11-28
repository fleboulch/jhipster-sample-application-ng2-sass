import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Site } from './site.model';
import { SitePopupService } from './site-popup.service';
import { SiteService } from './site.service';
import { Entreprise, EntrepriseService } from '../entreprise';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-site-dialog',
    templateUrl: './site-dialog.component.html'
})
export class SiteDialogComponent implements OnInit {

    site: Site;
    isSaving: boolean;

    entreprisesieges: Entreprise[];

    entreprises: Entreprise[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private siteService: SiteService,
        private entrepriseService: EntrepriseService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.entrepriseService
            .query({filter: 'siege(adresse)-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.site.entrepriseSiegeId) {
                    this.entreprisesieges = res.json;
                } else {
                    this.entrepriseService
                        .find(this.site.entrepriseSiegeId)
                        .subscribe((subRes: Entreprise) => {
                            this.entreprisesieges = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.entrepriseService.query()
            .subscribe((res: ResponseWrapper) => { this.entreprises = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.site.id !== undefined) {
            this.subscribeToSaveResponse(
                this.siteService.update(this.site));
        } else {
            this.subscribeToSaveResponse(
                this.siteService.create(this.site));
        }
    }

    private subscribeToSaveResponse(result: Observable<Site>) {
        result.subscribe((res: Site) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Site) {
        this.eventManager.broadcast({ name: 'siteListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackEntrepriseById(index: number, item: Entreprise) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-site-popup',
    template: ''
})
export class SitePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private sitePopupService: SitePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.sitePopupService
                    .open(SiteDialogComponent as Component, params['id']);
            } else {
                this.sitePopupService
                    .open(SiteDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
