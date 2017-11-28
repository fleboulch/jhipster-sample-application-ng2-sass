import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Tuteur } from './tuteur.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class TuteurService {

    private resourceUrl = SERVER_API_URL + 'api/tuteurs';

    constructor(private http: Http) { }

    create(tuteur: Tuteur): Observable<Tuteur> {
        const copy = this.convert(tuteur);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(tuteur: Tuteur): Observable<Tuteur> {
        const copy = this.convert(tuteur);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Tuteur> {
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
     * Convert a returned JSON object to Tuteur.
     */
    private convertItemFromServer(json: any): Tuteur {
        const entity: Tuteur = Object.assign(new Tuteur(), json);
        return entity;
    }

    /**
     * Convert a Tuteur to a JSON which can be sent to the server.
     */
    private convert(tuteur: Tuteur): Tuteur {
        const copy: Tuteur = Object.assign({}, tuteur);
        return copy;
    }
}
