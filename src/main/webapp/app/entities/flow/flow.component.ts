import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFlow } from 'app/shared/model/flow.model';
import { FlowService } from './flow.service';
import { FlowDeleteDialogComponent } from './flow-delete-dialog.component';

@Component({
  selector: 'jhi-flow',
  templateUrl: './flow.component.html',
})
export class FlowComponent implements OnInit, OnDestroy {
  flows?: IFlow[];
  eventSubscriber?: Subscription;

  constructor(protected flowService: FlowService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.flowService.query().subscribe((res: HttpResponse<IFlow[]>) => (this.flows = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInFlows();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IFlow): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInFlows(): void {
    this.eventSubscriber = this.eventManager.subscribe('flowListModification', () => this.loadAll());
  }

  delete(flow: IFlow): void {
    const modalRef = this.modalService.open(FlowDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.flow = flow;
  }
}
