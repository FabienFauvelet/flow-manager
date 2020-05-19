import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FlowManagerSharedModule } from 'app/shared/shared.module';
import { ComponentDetailsComponent } from './component-details.component';
import { ComponentDetailsDetailComponent } from './component-details-detail.component';
import { ComponentDetailsUpdateComponent } from './component-details-update.component';
import { ComponentDetailsDeleteDialogComponent } from './component-details-delete-dialog.component';
import { componentDetailsRoute } from './component-details.route';

@NgModule({
  imports: [FlowManagerSharedModule, RouterModule.forChild(componentDetailsRoute)],
  declarations: [
    ComponentDetailsComponent,
    ComponentDetailsDetailComponent,
    ComponentDetailsUpdateComponent,
    ComponentDetailsDeleteDialogComponent,
  ],
  entryComponents: [ComponentDetailsDeleteDialogComponent],
})
export class FlowManagerComponentDetailsModule {}
