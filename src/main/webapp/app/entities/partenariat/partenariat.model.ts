import { BaseEntity } from './../../shared';

export class Partenariat implements BaseEntity {
    constructor(
        public id?: number,
        public dateDebut?: any,
        public dateFin?: any,
        public diplomes?: BaseEntity[],
        public entrepriseId?: number,
    ) {
    }
}
