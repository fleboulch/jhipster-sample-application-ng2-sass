import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Professionnel } from './professionnel.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class ProfessionnelService {

    private resourceUrl = SERVER_API_URL + 'api/professionnels';

    constructor(private http: Http) { }

    create(professionnel: Professionnel): Observable<Professionnel> {
        const copy = this.convert(professionnel);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(professionnel: Professionnel): Observable<Professionnel> {
        const copy = this.convert(professionnel);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Professionnel> {
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
     * Convert a returned JSON object to Professionnel.
     */
    private convertItemFromServer(json: any): Professionnel {
        const entity: Professionnel = Object.assign(new Professionnel(), json);
        return entity;
    }

    /**
     * Convert a Professionnel to a JSON which can be sent to the server.
     */
    private convert(professionnel: Professionnel): Professionnel {
        const copy: Professionnel = Object.assign({}, professionnel);
        return copy;
    }
}
