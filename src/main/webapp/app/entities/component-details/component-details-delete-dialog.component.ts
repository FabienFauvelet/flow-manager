import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IComponentDetails } from 'app/shared/model/component-details.model';
import { ComponentDetailsService } from './component-details.service';

@Component({
  templateUrl: './component-details-delete-dialog.component.html',
})
export class ComponentDetailsDeleteDialogComponent {
  componentDetails?: IComponentDetails;

  constructor(
    protected componentDetailsService: ComponentDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.componentDetailsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('componentDetailsListModification');
      this.activeModal.close();
    });
  }
}
