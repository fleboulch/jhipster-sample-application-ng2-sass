import { BaseEntity } from './../../shared';

export class Entreprise implements BaseEntity {
    constructor(
        public id?: number,
        public nom?: string,
        public pays?: string,
        public numSiret?: string,
        public numSiren?: string,
        public telephone?: string,
        public dateCreation?: number,
        public dateModification?: number,
        public partenariats?: BaseEntity[],
        public sites?: BaseEntity[],
        public personnels?: BaseEntity[],
        public taxes?: BaseEntity[],
        public siegeId?: number,
        public contactId?: number,
        public groupeId?: number,
    ) {
    }
}
