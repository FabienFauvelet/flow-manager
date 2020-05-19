import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IComponentDetails } from 'app/shared/model/component-details.model';

type EntityResponseType = HttpResponse<IComponentDetails>;
type EntityArrayResponseType = HttpResponse<IComponentDetails[]>;

@Injectable({ providedIn: 'root' })
export class ComponentDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/component-details';

  constructor(protected http: HttpClient) {}

  create(componentDetails: IComponentDetails): Observable<EntityResponseType> {
    return this.http.post<IComponentDetails>(this.resourceUrl, componentDetails, { observe: 'response' });
  }

  update(componentDetails: IComponentDetails): Observable<EntityResponseType> {
    return this.http.put<IComponentDetails>(this.resourceUrl, componentDetails, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IComponentDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IComponentDetails[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
