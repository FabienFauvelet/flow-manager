import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFlow, Flow } from 'app/shared/model/flow.model';
import { FlowService } from './flow.service';
import { FlowComponent } from './flow.component';
import { FlowDetailComponent } from './flow-detail.component';
import { FlowUpdateComponent } from './flow-update.component';

@Injectable({ providedIn: 'root' })
export class FlowResolve implements Resolve<IFlow> {
  constructor(private service: FlowService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFlow> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((flow: HttpResponse<Flow>) => {
          if (flow.body) {
            return of(flow.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Flow());
  }
}

export const flowRoute: Routes = [
  {
    path: '',
    component: FlowComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'flowManagerApp.flow.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FlowDetailComponent,
    resolve: {
      flow: FlowResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'flowManagerApp.flow.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FlowUpdateComponent,
    resolve: {
      flow: FlowResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'flowManagerApp.flow.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FlowUpdateComponent,
    resolve: {
      flow: FlowResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'flowManagerApp.flow.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
