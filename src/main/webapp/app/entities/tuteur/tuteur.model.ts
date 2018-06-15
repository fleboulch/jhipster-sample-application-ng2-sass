import { BaseEntity } from './../../shared';

export class Tuteur implements BaseEntity {
    constructor(
        public id?: number,
        public nom?: string,
        public prenom?: string,
        public telephone?: string,
        public mail?: string,
        public conventionStages?: BaseEntity[],
    ) {
    }
}
