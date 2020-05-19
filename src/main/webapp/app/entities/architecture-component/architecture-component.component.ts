import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IArchitectureComponent } from 'app/shared/model/architecture-component.model';
import { ArchitectureComponentService } from './architecture-component.service';
import { ArchitectureComponentDeleteDialogComponent } from './architecture-component-delete-dialog.component';

@Component({
  selector: 'jhi-architecture-component',
  templateUrl: './architecture-component.component.html',
})
export class ArchitectureComponentComponent implements OnInit, OnDestroy {
  architectureComponents?: IArchitectureComponent[];
  eventSubscriber?: Subscription;

  constructor(
    protected architectureComponentService: ArchitectureComponentService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.architectureComponentService
      .query()
      .subscribe((res: HttpResponse<IArchitectureComponent[]>) => (this.architectureComponents = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInArchitectureComponents();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IArchitectureComponent): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInArchitectureComponents(): void {
    this.eventSubscriber = this.eventManager.subscribe('architectureComponentListModification', () => this.loadAll());
  }

  delete(architectureComponent: IArchitectureComponent): void {
    const modalRef = this.modalService.open(ArchitectureComponentDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.architectureComponent = architectureComponent;
  }
}
