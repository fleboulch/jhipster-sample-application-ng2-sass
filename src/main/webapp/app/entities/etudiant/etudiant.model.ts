import { BaseEntity } from './../../shared';

export const enum Sexe {
    'HOMME',
    'FEMME'
}

export class Etudiant implements BaseEntity {
    constructor(
        public id?: number,
        public nom?: string,
        public prenom?: string,
        public mail?: string,
        public sexe?: Sexe,
        public numEtudiant?: string,
        public conventionStages?: BaseEntity[],
        public promotions?: BaseEntity[],
    ) {
    }
}
