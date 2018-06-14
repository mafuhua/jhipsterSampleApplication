import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Task2MySuffix } from './task-2-my-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Task2MySuffix>;

@Injectable()
export class Task2MySuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/task-2-s';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/task-2-s';

    constructor(private http: HttpClient) { }

    create(task2: Task2MySuffix): Observable<EntityResponseType> {
        const copy = this.convert(task2);
        return this.http.post<Task2MySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(task2: Task2MySuffix): Observable<EntityResponseType> {
        const copy = this.convert(task2);
        return this.http.put<Task2MySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Task2MySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Task2MySuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<Task2MySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Task2MySuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<Task2MySuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<Task2MySuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Task2MySuffix[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Task2MySuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Task2MySuffix[]>): HttpResponse<Task2MySuffix[]> {
        const jsonResponse: Task2MySuffix[] = res.body;
        const body: Task2MySuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Task2MySuffix.
     */
    private convertItemFromServer(task2: Task2MySuffix): Task2MySuffix {
        const copy: Task2MySuffix = Object.assign({}, task2);
        return copy;
    }

    /**
     * Convert a Task2MySuffix to a JSON which can be sent to the server.
     */
    private convert(task2: Task2MySuffix): Task2MySuffix {
        const copy: Task2MySuffix = Object.assign({}, task2);
        return copy;
    }
}
