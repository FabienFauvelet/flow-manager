import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IComponentCategory } from 'app/shared/model/component-category.model';
import { ComponentCategoryService } from './component-category.service';
import { ComponentCategoryDeleteDialogComponent } from './component-category-delete-dialog.component';

@Component({
  selector: 'jhi-component-category',
  templateUrl: './component-category.component.html',
})
export class ComponentCategoryComponent implements OnInit, OnDestroy {
  componentCategories?: IComponentCategory[];
  eventSubscriber?: Subscription;

  constructor(
    protected componentCategoryService: ComponentCategoryService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.componentCategoryService
      .query()
      .subscribe((res: HttpResponse<IComponentCategory[]>) => (this.componentCategories = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInComponentCategories();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IComponentCategory): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInComponentCategories(): void {
    this.eventSubscriber = this.eventManager.subscribe('componentCategoryListModification', () => this.loadAll());
  }

  delete(componentCategory: IComponentCategory): void {
    const modalRef = this.modalService.open(ComponentCategoryDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.componentCategory = componentCategory;
  }
}
