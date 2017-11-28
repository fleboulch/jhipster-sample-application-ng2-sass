import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Promotion } from './promotion.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class PromotionService {

    private resourceUrl = SERVER_API_URL + 'api/promotions';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(promotion: Promotion): Observable<Promotion> {
        const copy = this.convert(promotion);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(promotion: Promotion): Observable<Promotion> {
        const copy = this.convert(promotion);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Promotion> {
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
     * Convert a returned JSON object to Promotion.
     */
    private convertItemFromServer(json: any): Promotion {
        const entity: Promotion = Object.assign(new Promotion(), json);
        entity.annee = this.dateUtils
            .convertDateTimeFromServer(json.annee);
        return entity;
    }

    /**
     * Convert a Promotion to a JSON which can be sent to the server.
     */
    private convert(promotion: Promotion): Promotion {
        const copy: Promotion = Object.assign({}, promotion);

        copy.annee = this.dateUtils.toDate(promotion.annee);
        return copy;
    }
}
