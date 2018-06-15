import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ConventionStage } from './convention-stage.model';
import { ConventionStagePopupService } from './convention-stage-popup.service';
import { ConventionStageService } from './convention-stage.service';
import { Etudiant, EtudiantService } from '../etudiant';
import { Site, SiteService } from '../site';
import { Tuteur, TuteurService } from '../tuteur';
import { Professionnel, ProfessionnelService } from '../professionnel';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-convention-stage-dialog',
    templateUrl: './convention-stage-dialog.component.html'
})
export class ConventionStageDialogComponent implements OnInit {

    conventionStage: ConventionStage;
    isSaving: boolean;

    etudiants: Etudiant[];

    sites: Site[];

    tuteurs: Tuteur[];

    professionnels: Professionnel[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private conventionStageService: ConventionStageService,
        private etudiantService: EtudiantService,
        private siteService: SiteService,
        private tuteurService: TuteurService,
        private professionnelService: ProfessionnelService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.etudiantService.query()
            .subscribe((res: ResponseWrapper) => { this.etudiants = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.siteService.query()
            .subscribe((res: ResponseWrapper) => { this.sites = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.tuteurService.query()
            .subscribe((res: ResponseWrapper) => { this.tuteurs = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.professionnelService.query()
            .subscribe((res: ResponseWrapper) => { this.professionnels = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.conventionStage.id !== undefined) {
            this.subscribeToSaveResponse(
                this.conventionStageService.update(this.conventionStage));
        } else {
            this.subscribeToSaveResponse(
                this.conventionStageService.create(this.conventionStage));
        }
    }

    private subscribeToSaveResponse(result: Observable<ConventionStage>) {
        result.subscribe((res: ConventionStage) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: ConventionStage) {
        this.eventManager.broadcast({ name: 'conventionStageListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackEtudiantById(index: number, item: Etudiant) {
        return item.id;
    }

    trackSiteById(index: number, item: Site) {
        return item.id;
    }

    trackTuteurById(index: number, item: Tuteur) {
        return item.id;
    }

    trackProfessionnelById(index: number, item: Professionnel) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-convention-stage-popup',
    template: ''
})
export class ConventionStagePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private conventionStagePopupService: ConventionStagePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.conventionStagePopupService
                    .open(ConventionStageDialogComponent as Component, params['id']);
            } else {
                this.conventionStagePopupService
                    .open(ConventionStageDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
