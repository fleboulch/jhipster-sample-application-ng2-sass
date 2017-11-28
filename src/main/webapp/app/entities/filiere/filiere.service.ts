import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Filiere } from './filiere.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class FiliereService {

    private resourceUrl = SERVER_API_URL + 'api/filieres';

    constructor(private http: Http) { }

    create(filiere: Filiere): Observable<Filiere> {
        const copy = this.convert(filiere);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(filiere: Filiere): Observable<Filiere> {
        const copy = this.convert(filiere);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Filiere> {
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
     * Convert a returned JSON object to Filiere.
     */
    private convertItemFromServer(json: any): Filiere {
        const entity: Filiere = Object.assign(new Filiere(), json);
        return entity;
    }

    /**
     * Convert a Filiere to a JSON which can be sent to the server.
     */
    private convert(filiere: Filiere): Filiere {
        const copy: Filiere = Object.assign({}, filiere);
        return copy;
    }
}
