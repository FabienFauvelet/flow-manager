import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'project',
        loadChildren: () => import('./project/project.module').then(m => m.FlowManagerProjectModule),
      },
      {
        path: 'architecture-component',
        loadChildren: () =>
          import('./architecture-component/architecture-component.module').then(m => m.FlowManagerArchitectureComponentModule),
      },
      {
        path: 'component-category',
        loadChildren: () => import('./component-category/component-category.module').then(m => m.FlowManagerComponentCategoryModule),
      },
      {
        path: 'flow',
        loadChildren: () => import('./flow/flow.module').then(m => m.FlowManagerFlowModule),
      },
      {
        path: 'component-details',
        loadChildren: () => import('./component-details/component-details.module').then(m => m.FlowManagerComponentDetailsModule),
      },
      {
        path: 'environment',
        loadChildren: () => import('./environment/environment.module').then(m => m.FlowManagerEnvironmentModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class FlowManagerEntityModule {}
