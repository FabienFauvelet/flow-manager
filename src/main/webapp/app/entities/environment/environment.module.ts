import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FlowManagerSharedModule } from 'app/shared/shared.module';
import { EnvironmentComponent } from './environment.component';
import { EnvironmentDetailComponent } from './environment-detail.component';
import { EnvironmentUpdateComponent } from './environment-update.component';
import { EnvironmentDeleteDialogComponent } from './environment-delete-dialog.component';
import { environmentRoute } from './environment.route';

@NgModule({
  imports: [FlowManagerSharedModule, RouterModule.forChild(environmentRoute)],
  declarations: [EnvironmentComponent, EnvironmentDetailComponent, EnvironmentUpdateComponent, EnvironmentDeleteDialogComponent],
  entryComponents: [EnvironmentDeleteDialogComponent],
})
export class FlowManagerEnvironmentModule {}
