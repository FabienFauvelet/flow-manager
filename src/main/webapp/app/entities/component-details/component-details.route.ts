import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IComponentDetails, ComponentDetails } from 'app/shared/model/component-details.model';
import { ComponentDetailsService } from './component-details.service';
import { ComponentDetailsComponent } from './component-details.component';
import { ComponentDetailsDetailComponent } from './component-details-detail.component';
import { ComponentDetailsUpdateComponent } from './component-details-update.component';

@Injectable({ providedIn: 'root' })
export class ComponentDetailsResolve implements Resolve<IComponentDetails> {
  constructor(private service: ComponentDetailsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IComponentDetails> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((componentDetails: HttpResponse<ComponentDetails>) => {
          if (componentDetails.body) {
            return of(componentDetails.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ComponentDetails());
  }
}

export const componentDetailsRoute: Routes = [
  {
    path: '',
    component: ComponentDetailsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'flowManagerApp.componentDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ComponentDetailsDetailComponent,
    resolve: {
      componentDetails: ComponentDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'flowManagerApp.componentDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ComponentDetailsUpdateComponent,
    resolve: {
      componentDetails: ComponentDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'flowManagerApp.componentDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ComponentDetailsUpdateComponent,
    resolve: {
      componentDetails: ComponentDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'flowManagerApp.componentDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
