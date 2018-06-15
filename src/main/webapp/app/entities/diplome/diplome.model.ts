import { BaseEntity } from './../../shared';

export class Diplome implements BaseEntity {
    constructor(
        public id?: number,
        public nom?: string,
        public dateCreation?: number,
        public dateModification?: number,
        public filieres?: BaseEntity[],
        public partenariats?: BaseEntity[],
        public intervenants?: BaseEntity[],
    ) {
    }
}
