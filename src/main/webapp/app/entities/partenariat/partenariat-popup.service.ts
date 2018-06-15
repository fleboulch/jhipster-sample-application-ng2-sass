import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { Partenariat } from './partenariat.model';
import { PartenariatService } from './partenariat.service';

@Injectable()
export class PartenariatPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private partenariatService: PartenariatService

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
                this.partenariatService.find(id).subscribe((partenariat) => {
                    partenariat.dateDebut = this.datePipe
                        .transform(partenariat.dateDebut, 'yyyy-MM-ddTHH:mm:ss');
                    partenariat.dateFin = this.datePipe
                        .transform(partenariat.dateFin, 'yyyy-MM-ddTHH:mm:ss');
                    this.ngbModalRef = this.partenariatModalRef(component, partenariat);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.partenariatModalRef(component, new Partenariat());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    partenariatModalRef(component: Component, partenariat: Partenariat): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.partenariat = partenariat;
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
