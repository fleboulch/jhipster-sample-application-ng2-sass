import { BaseEntity } from './../../shared';

export class Groupe implements BaseEntity {
    constructor(
        public id?: number,
        public nom?: string,
        public dateCreation?: number,
        public dateModification?: number,
        public entreprises?: BaseEntity[],
    ) {
    }
}
