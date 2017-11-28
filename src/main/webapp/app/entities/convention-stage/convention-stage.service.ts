import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { ConventionStage } from './convention-stage.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class ConventionStageService {

    private resourceUrl = SERVER_API_URL + 'api/convention-stages';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(conventionStage: ConventionStage): Observable<ConventionStage> {
        const copy = this.convert(conventionStage);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(conventionStage: ConventionStage): Observable<ConventionStage> {
        const copy = this.convert(conventionStage);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<ConventionStage> {
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
     * Convert a returned JSON object to ConventionStage.
     */
    private convertItemFromServer(json: any): ConventionStage {
        const entity: ConventionStage = Object.assign(new ConventionStage(), json);
        entity.dateDebut = this.dateUtils
            .convertDateTimeFromServer(json.dateDebut);
        entity.dateFin = this.dateUtils
            .convertDateTimeFromServer(json.dateFin);
        return entity;
    }

    /**
     * Convert a ConventionStage to a JSON which can be sent to the server.
     */
    private convert(conventionStage: ConventionStage): ConventionStage {
        const copy: ConventionStage = Object.assign({}, conventionStage);

        copy.dateDebut = this.dateUtils.toDate(conventionStage.dateDebut);

        copy.dateFin = this.dateUtils.toDate(conventionStage.dateFin);
        return copy;
    }
}
