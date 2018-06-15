import { BaseEntity } from './../../shared';

export class Filiere implements BaseEntity {
    constructor(
        public id?: number,
        public niveau?: string,
        public promotions?: BaseEntity[],
        public diplomeId?: number,
    ) {
    }
}
