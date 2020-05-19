import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FlowManagerSharedModule } from 'app/shared/shared.module';
import { ArchitectureComponentComponent } from './architecture-component.component';
import { ArchitectureComponentDetailComponent } from './architecture-component-detail.component';
import { ArchitectureComponentUpdateComponent } from './architecture-component-update.component';
import { ArchitectureComponentDeleteDialogComponent } from './architecture-component-delete-dialog.component';
import { architectureComponentRoute } from './architecture-component.route';

@NgModule({
  imports: [FlowManagerSharedModule, RouterModule.forChild(architectureComponentRoute)],
  declarations: [
    ArchitectureComponentComponent,
    ArchitectureComponentDetailComponent,
    ArchitectureComponentUpdateComponent,
    ArchitectureComponentDeleteDialogComponent,
  ],
  entryComponents: [ArchitectureComponentDeleteDialogComponent],
})
export class FlowManagerArchitectureComponentModule {}
