import { BaseEntity } from './../../shared';

export class Taxe implements BaseEntity {
    constructor(
        public id?: number,
        public montant?: number,
        public annee?: any,
        public entrepriseId?: number,
    ) {
    }
}
