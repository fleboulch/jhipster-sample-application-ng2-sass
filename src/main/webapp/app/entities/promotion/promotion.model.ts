import { BaseEntity } from './../../shared';

export class Promotion implements BaseEntity {
    constructor(
        public id?: number,
        public annee?: any,
        public filiereId?: number,
        public etudiants?: BaseEntity[],
    ) {
    }
}
