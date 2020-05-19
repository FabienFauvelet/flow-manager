import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IComponentCategory } from 'app/shared/model/component-category.model';
import { ComponentCategoryService } from './component-category.service';

@Component({
  templateUrl: './component-category-delete-dialog.component.html',
})
export class ComponentCategoryDeleteDialogComponent {
  componentCategory?: IComponentCategory;

  constructor(
    protected componentCategoryService: ComponentCategoryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.componentCategoryService.delete(id).subscribe(() => {
      this.eventManager.broadcast('componentCategoryListModification');
      this.activeModal.close();
    });
  }
}
