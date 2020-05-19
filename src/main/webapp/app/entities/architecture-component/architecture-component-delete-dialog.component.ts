import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IArchitectureComponent } from 'app/shared/model/architecture-component.model';
import { ArchitectureComponentService } from './architecture-component.service';

@Component({
  templateUrl: './architecture-component-delete-dialog.component.html',
})
export class ArchitectureComponentDeleteDialogComponent {
  architectureComponent?: IArchitectureComponent;

  constructor(
    protected architectureComponentService: ArchitectureComponentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.architectureComponentService.delete(id).subscribe(() => {
      this.eventManager.broadcast('architectureComponentListModification');
      this.activeModal.close();
    });
  }
}
