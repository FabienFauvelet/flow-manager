import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEnvironment } from 'app/shared/model/environment.model';
import { EnvironmentService } from './environment.service';

@Component({
  templateUrl: './environment-delete-dialog.component.html',
})
export class EnvironmentDeleteDialogComponent {
  environment?: IEnvironment;

  constructor(
    protected environmentService: EnvironmentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.environmentService.delete(id).subscribe(() => {
      this.eventManager.broadcast('environmentListModification');
      this.activeModal.close();
    });
  }
}
