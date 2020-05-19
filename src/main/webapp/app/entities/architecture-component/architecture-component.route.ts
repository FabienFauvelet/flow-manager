import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IArchitectureComponent, ArchitectureComponent } from 'app/shared/model/architecture-component.model';
import { ArchitectureComponentService } from './architecture-component.service';
import { ArchitectureComponentComponent } from './architecture-component.component';
import { ArchitectureComponentDetailComponent } from './architecture-component-detail.component';
import { ArchitectureComponentUpdateComponent } from './architecture-component-update.component';

@Injectable({ providedIn: 'root' })
export class ArchitectureComponentResolve implements Resolve<IArchitectureComponent> {
  constructor(private service: ArchitectureComponentService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IArchitectureComponent> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((architectureComponent: HttpResponse<ArchitectureComponent>) => {
          if (architectureComponent.body) {
            return of(architectureComponent.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ArchitectureComponent());
  }
}

export const architectureComponentRoute: Routes = [
  {
    path: '',
    component: ArchitectureComponentComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'flowManagerApp.architectureComponent.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ArchitectureComponentDetailComponent,
    resolve: {
      architectureComponent: ArchitectureComponentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'flowManagerApp.architectureComponent.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ArchitectureComponentUpdateComponent,
    resolve: {
      architectureComponent: ArchitectureComponentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'flowManagerApp.architectureComponent.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ArchitectureComponentUpdateComponent,
    resolve: {
      architectureComponent: ArchitectureComponentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'flowManagerApp.architectureComponent.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
