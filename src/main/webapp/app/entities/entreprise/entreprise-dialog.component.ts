import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Entreprise } from './entreprise.model';
import { EntreprisePopupService } from './entreprise-popup.service';
import { EntrepriseService } from './entreprise.service';
import { Site, SiteService } from '../site';
import { Professionnel, ProfessionnelService } from '../professionnel';
import { Groupe, GroupeService } from '../groupe';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-entreprise-dialog',
    templateUrl: './entreprise-dialog.component.html'
})
export class EntrepriseDialogComponent implements OnInit {

    entreprise: Entreprise;
    isSaving: boolean;

    sites: Site[];

    professionnels: Professionnel[];

    groupes: Groupe[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private entrepriseService: EntrepriseService,
        private siteService: SiteService,
        private professionnelService: ProfessionnelService,
        private groupeService: GroupeService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.siteService.query()
            .subscribe((res: ResponseWrapper) => { this.sites = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.professionnelService.query()
            .subscribe((res: ResponseWrapper) => { this.professionnels = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.groupeService.query()
            .subscribe((res: ResponseWrapper) => { this.groupes = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.entreprise.id !== undefined) {
            this.subscribeToSaveResponse(
                this.entrepriseService.update(this.entreprise));
        } else {
            this.subscribeToSaveResponse(
                this.entrepriseService.create(this.entreprise));
        }
    }

    private subscribeToSaveResponse(result: Observable<Entreprise>) {
        result.subscribe((res: Entreprise) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Entreprise) {
        this.eventManager.broadcast({ name: 'entrepriseListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackSiteById(index: number, item: Site) {
        return item.id;
    }

    trackProfessionnelById(index: number, item: Professionnel) {
        return item.id;
    }

    trackGroupeById(index: number, item: Groupe) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-entreprise-popup',
    template: ''
})
export class EntreprisePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private entreprisePopupService: EntreprisePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.entreprisePopupService
                    .open(EntrepriseDialogComponent as Component, params['id']);
            } else {
                this.entreprisePopupService
                    .open(EntrepriseDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
