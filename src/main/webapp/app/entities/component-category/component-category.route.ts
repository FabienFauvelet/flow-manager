import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IComponentCategory, ComponentCategory } from 'app/shared/model/component-category.model';
import { ComponentCategoryService } from './component-category.service';
import { ComponentCategoryComponent } from './component-category.component';
import { ComponentCategoryDetailComponent } from './component-category-detail.component';
import { ComponentCategoryUpdateComponent } from './component-category-update.component';

@Injectable({ providedIn: 'root' })
export class ComponentCategoryResolve implements Resolve<IComponentCategory> {
  constructor(private service: ComponentCategoryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IComponentCategory> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((componentCategory: HttpResponse<ComponentCategory>) => {
          if (componentCategory.body) {
            return of(componentCategory.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ComponentCategory());
  }
}

export const componentCategoryRoute: Routes = [
  {
    path: '',
    component: ComponentCategoryComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'flowManagerApp.componentCategory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ComponentCategoryDetailComponent,
    resolve: {
      componentCategory: ComponentCategoryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'flowManagerApp.componentCategory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ComponentCategoryUpdateComponent,
    resolve: {
      componentCategory: ComponentCategoryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'flowManagerApp.componentCategory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ComponentCategoryUpdateComponent,
    resolve: {
      componentCategory: ComponentCategoryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'flowManagerApp.componentCategory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
