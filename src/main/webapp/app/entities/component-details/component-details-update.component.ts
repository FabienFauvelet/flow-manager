import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IComponentDetails, ComponentDetails } from 'app/shared/model/component-details.model';
import { ComponentDetailsService } from './component-details.service';
import { IEnvironment } from 'app/shared/model/environment.model';
import { EnvironmentService } from 'app/entities/environment/environment.service';
import { IArchitectureComponent } from 'app/shared/model/architecture-component.model';
import { ArchitectureComponentService } from 'app/entities/architecture-component/architecture-component.service';

type SelectableEntity = IEnvironment | IArchitectureComponent;

@Component({
  selector: 'jhi-component-details-update',
  templateUrl: './component-details-update.component.html',
})
export class ComponentDetailsUpdateComponent implements OnInit {
  isSaving = false;
  environments: IEnvironment[] = [];
  architecturecomponents: IArchitectureComponent[] = [];

  editForm = this.fb.group({
    id: [],
    server: [],
    port: [],
    url: [],
    environmentId: [],
    architectureComponentId: [],
    detailsByEnvironementId: [],
    componentDetailsId: [],
  });

  constructor(
    protected componentDetailsService: ComponentDetailsService,
    protected environmentService: EnvironmentService,
    protected architectureComponentService: ArchitectureComponentService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ componentDetails }) => {
      this.updateForm(componentDetails);

      this.environmentService.query().subscribe((res: HttpResponse<IEnvironment[]>) => (this.environments = res.body || []));

      this.architectureComponentService
        .query()
        .subscribe((res: HttpResponse<IArchitectureComponent[]>) => (this.architecturecomponents = res.body || []));
    });
  }

  updateForm(componentDetails: IComponentDetails): void {
    this.editForm.patchValue({
      id: componentDetails.id,
      server: componentDetails.server,
      port: componentDetails.port,
      url: componentDetails.url,
      environmentId: componentDetails.environmentId,
      architectureComponentId: componentDetails.architectureComponentId,
      detailsByEnvironementId: componentDetails.detailsByEnvironementId,
      componentDetailsId: componentDetails.componentDetailsId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const componentDetails = this.createFromForm();
    if (componentDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.componentDetailsService.update(componentDetails));
    } else {
      this.subscribeToSaveResponse(this.componentDetailsService.create(componentDetails));
    }
  }

  private createFromForm(): IComponentDetails {
    return {
      ...new ComponentDetails(),
      id: this.editForm.get(['id'])!.value,
      server: this.editForm.get(['server'])!.value,
      port: this.editForm.get(['port'])!.value,
      url: this.editForm.get(['url'])!.value,
      environmentId: this.editForm.get(['environmentId'])!.value,
      architectureComponentId: this.editForm.get(['architectureComponentId'])!.value,
      detailsByEnvironementId: this.editForm.get(['detailsByEnvironementId'])!.value,
      componentDetailsId: this.editForm.get(['componentDetailsId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IComponentDetails>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
