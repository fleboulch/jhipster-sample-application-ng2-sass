import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Entreprise } from './entreprise.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class EntrepriseService {

    private resourceUrl = SERVER_API_URL + 'api/entreprises';

    constructor(private http: Http) { }

    create(entreprise: Entreprise): Observable<Entreprise> {
        const copy = this.convert(entreprise);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(entreprise: Entreprise): Observable<Entreprise> {
        const copy = this.convert(entreprise);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Entreprise> {
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
     * Convert a returned JSON object to Entreprise.
     */
    private convertItemFromServer(json: any): Entreprise {
        const entity: Entreprise = Object.assign(new Entreprise(), json);
        return entity;
    }

    /**
     * Convert a Entreprise to a JSON which can be sent to the server.
     */
    private convert(entreprise: Entreprise): Entreprise {
        const copy: Entreprise = Object.assign({}, entreprise);
        return copy;
    }
}
