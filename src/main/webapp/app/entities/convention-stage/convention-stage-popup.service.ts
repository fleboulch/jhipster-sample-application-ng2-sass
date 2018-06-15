import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { ConventionStage } from './convention-stage.model';
import { ConventionStageService } from './convention-stage.service';

@Injectable()
export class ConventionStagePopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private conventionStageService: ConventionStageService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.conventionStageService.find(id).subscribe((conventionStage) => {
                    conventionStage.dateDebut = this.datePipe
                        .transform(conventionStage.dateDebut, 'yyyy-MM-ddTHH:mm:ss');
                    conventionStage.dateFin = this.datePipe
                        .transform(conventionStage.dateFin, 'yyyy-MM-ddTHH:mm:ss');
                    this.ngbModalRef = this.conventionStageModalRef(component, conventionStage);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.conventionStageModalRef(component, new ConventionStage());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    conventionStageModalRef(component: Component, conventionStage: ConventionStage): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.conventionStage = conventionStage;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
