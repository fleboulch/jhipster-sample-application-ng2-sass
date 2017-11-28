import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Diplome } from './diplome.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class DiplomeService {

    private resourceUrl = SERVER_API_URL + 'api/diplomes';

    constructor(private http: Http) { }

    create(diplome: Diplome): Observable<Diplome> {
        const copy = this.convert(diplome);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(diplome: Diplome): Observable<Diplome> {
        const copy = this.convert(diplome);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Diplome> {
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
     * Convert a returned JSON object to Diplome.
     */
    private convertItemFromServer(json: any): Diplome {
        const entity: Diplome = Object.assign(new Diplome(), json);
        return entity;
    }

    /**
     * Convert a Diplome to a JSON which can be sent to the server.
     */
    private convert(diplome: Diplome): Diplome {
        const copy: Diplome = Object.assign({}, diplome);
        return copy;
    }
}
