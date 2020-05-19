import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEnvironment } from 'app/shared/model/environment.model';
import { EnvironmentService } from './environment.service';
import { EnvironmentDeleteDialogComponent } from './environment-delete-dialog.component';

@Component({
  selector: 'jhi-environment',
  templateUrl: './environment.component.html',
})
export class EnvironmentComponent implements OnInit, OnDestroy {
  environments?: IEnvironment[];
  eventSubscriber?: Subscription;

  constructor(
    protected environmentService: EnvironmentService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.environmentService.query().subscribe((res: HttpResponse<IEnvironment[]>) => (this.environments = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEnvironments();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEnvironment): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEnvironments(): void {
    this.eventSubscriber = this.eventManager.subscribe('environmentListModification', () => this.loadAll());
  }

  delete(environment: IEnvironment): void {
    const modalRef = this.modalService.open(EnvironmentDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.environment = environment;
  }
}
