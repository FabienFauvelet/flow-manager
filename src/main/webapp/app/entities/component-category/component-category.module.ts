import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FlowManagerSharedModule } from 'app/shared/shared.module';
import { ComponentCategoryComponent } from './component-category.component';
import { ComponentCategoryDetailComponent } from './component-category-detail.component';
import { ComponentCategoryUpdateComponent } from './component-category-update.component';
import { ComponentCategoryDeleteDialogComponent } from './component-category-delete-dialog.component';
import { componentCategoryRoute } from './component-category.route';

@NgModule({
  imports: [FlowManagerSharedModule, RouterModule.forChild(componentCategoryRoute)],
  declarations: [
    ComponentCategoryComponent,
    ComponentCategoryDetailComponent,
    ComponentCategoryUpdateComponent,
    ComponentCategoryDeleteDialogComponent,
  ],
  entryComponents: [ComponentCategoryDeleteDialogComponent],
})
export class FlowManagerComponentCategoryModule {}
