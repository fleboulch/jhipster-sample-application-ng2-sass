import { BaseEntity } from './../../shared';

export class ConventionStage implements BaseEntity {
    constructor(
        public id?: number,
        public sujet?: string,
        public fonctions?: string,
        public competences?: string,
        public dateDebut?: any,
        public dateFin?: any,
        public etudiantId?: number,
        public lieuStageId?: number,
        public tuteurId?: number,
        public maitreStageId?: number,
    ) {
    }
}
