import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IComponentDetails } from 'app/shared/model/component-details.model';
import { ComponentDetailsService } from './component-details.service';
import { ComponentDetailsDeleteDialogComponent } from './component-details-delete-dialog.component';

@Component({
  selector: 'jhi-component-details',
  templateUrl: './component-details.component.html',
})
export class ComponentDetailsComponent implements OnInit, OnDestroy {
  componentDetails?: IComponentDetails[];
  eventSubscriber?: Subscription;

  constructor(
    protected componentDetailsService: ComponentDetailsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.componentDetailsService.query().subscribe((res: HttpResponse<IComponentDetails[]>) => (this.componentDetails = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInComponentDetails();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IComponentDetails): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInComponentDetails(): void {
    this.eventSubscriber = this.eventManager.subscribe('componentDetailsListModification', () => this.loadAll());
  }

  delete(componentDetails: IComponentDetails): void {
    const modalRef = this.modalService.open(ComponentDetailsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.componentDetails = componentDetails;
  }
}
