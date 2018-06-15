import { BaseEntity } from './../../shared';

export class Professionnel implements BaseEntity {
    constructor(
        public id?: number,
        public nom?: string,
        public prenom?: string,
        public telephone?: string,
        public mail?: string,
        public fonction?: string,
        public ancienEtudiant?: boolean,
        public dateCreation?: number,
        public dateModification?: number,
        public entrepriseContactId?: number,
        public conventionStages?: BaseEntity[],
        public diplomes?: BaseEntity[],
        public entreprisePersonnelId?: number,
    ) {
        this.ancienEtudiant = false;
    }
}
