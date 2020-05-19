import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IArchitectureComponent } from 'app/shared/model/architecture-component.model';

type EntityResponseType = HttpResponse<IArchitectureComponent>;
type EntityArrayResponseType = HttpResponse<IArchitectureComponent[]>;

@Injectable({ providedIn: 'root' })
export class ArchitectureComponentService {
  public resourceUrl = SERVER_API_URL + 'api/architecture-components';

  constructor(protected http: HttpClient) {}

  create(architectureComponent: IArchitectureComponent): Observable<EntityResponseType> {
    return this.http.post<IArchitectureComponent>(this.resourceUrl, architectureComponent, { observe: 'response' });
  }

  update(architectureComponent: IArchitectureComponent): Observable<EntityResponseType> {
    return this.http.put<IArchitectureComponent>(this.resourceUrl, architectureComponent, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IArchitectureComponent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IArchitectureComponent[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
