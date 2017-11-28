import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Partenariat } from './partenariat.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class PartenariatService {

    private resourceUrl = SERVER_API_URL + 'api/partenariats';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(partenariat: Partenariat): Observable<Partenariat> {
        const copy = this.convert(partenariat);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(partenariat: Partenariat): Observable<Partenariat> {
        const copy = this.convert(partenariat);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Partenariat> {
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
     * Convert a returned JSON object to Partenariat.
     */
    private convertItemFromServer(json: any): Partenariat {
        const entity: Partenariat = Object.assign(new Partenariat(), json);
        entity.dateDebut = this.dateUtils
            .convertDateTimeFromServer(json.dateDebut);
        entity.dateFin = this.dateUtils
            .convertDateTimeFromServer(json.dateFin);
        return entity;
    }

    /**
     * Convert a Partenariat to a JSON which can be sent to the server.
     */
    private convert(partenariat: Partenariat): Partenariat {
        const copy: Partenariat = Object.assign({}, partenariat);

        copy.dateDebut = this.dateUtils.toDate(partenariat.dateDebut);

        copy.dateFin = this.dateUtils.toDate(partenariat.dateFin);
        return copy;
    }
}
