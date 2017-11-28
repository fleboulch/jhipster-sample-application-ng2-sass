import { BaseEntity } from './../../shared';

export class Site implements BaseEntity {
    constructor(
        public id?: number,
        public adresse?: string,
        public codePostal?: string,
        public ville?: string,
        public pays?: string,
        public telephone?: string,
        public dateCreation?: number,
        public dateModification?: number,
        public entrepriseSiegeId?: number,
        public conventionStages?: BaseEntity[],
        public entrepriseSiteId?: number,
    ) {
    }
}
