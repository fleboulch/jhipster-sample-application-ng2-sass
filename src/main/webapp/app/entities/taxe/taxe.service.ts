import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Taxe } from './taxe.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class TaxeService {

    private resourceUrl = SERVER_API_URL + 'api/taxes';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(taxe: Taxe): Observable<Taxe> {
        const copy = this.convert(taxe);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(taxe: Taxe): Observable<Taxe> {
        const copy = this.convert(taxe);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Taxe> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to Taxe.
     */
    private convertItemFromServer(json: any): Taxe {
        const entity: Taxe = Object.assign(new Taxe(), json);
        entity.annee = this.dateUtils
            .convertDateTimeFromServer(json.annee);
        return entity;
    }

    /**
     * Convert a Taxe to a JSON which can be sent to the server.
     */
    private convert(taxe: Taxe): Taxe {
        const copy: Taxe = Object.assign({}, taxe);

        copy.annee = this.dateUtils.toDate(taxe.annee);
        return copy;
    }
}
