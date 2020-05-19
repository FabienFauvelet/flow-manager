import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFlow, Flow } from 'app/shared/model/flow.model';
import { FlowService } from './flow.service';
import { IArchitectureComponent } from 'app/shared/model/architecture-component.model';
import { ArchitectureComponentService } from 'app/entities/architecture-component/architecture-component.service';
import { IProject } from 'app/shared/model/project.model';
import { ProjectService } from 'app/entities/project/project.service';

type SelectableEntity = IArchitectureComponent | IProject;

@Component({
  selector: 'jhi-flow-update',
  templateUrl: './flow-update.component.html',
})
export class FlowUpdateComponent implements OnInit {
  isSaving = false;
  architecturecomponents: IArchitectureComponent[] = [];
  projects: IProject[] = [];

  editForm = this.fb.group({
    id: [],
    description: [],
    status: [],
    architectureComponentId: [],
    architectureComponentId: [],
    flowsId: [],
  });

  constructor(
    protected flowService: FlowService,
    protected architectureComponentService: ArchitectureComponentService,
    protected projectService: ProjectService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ flow }) => {
      this.updateForm(flow);

      this.architectureComponentService
        .query()
        .subscribe((res: HttpResponse<IArchitectureComponent[]>) => (this.architecturecomponents = res.body || []));

      this.projectService.query().subscribe((res: HttpResponse<IProject[]>) => (this.projects = res.body || []));
    });
  }

  updateForm(flow: IFlow): void {
    this.editForm.patchValue({
      id: flow.id,
      description: flow.description,
      status: flow.status,
      architectureComponentId: flow.architectureComponentId,
      architectureComponentId: flow.architectureComponentId,
      flowsId: flow.flowsId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const flow = this.createFromForm();
    if (flow.id !== undefined) {
      this.subscribeToSaveResponse(this.flowService.update(flow));
    } else {
      this.subscribeToSaveResponse(this.flowService.create(flow));
    }
  }

  private createFromForm(): IFlow {
    return {
      ...new Flow(),
      id: this.editForm.get(['id'])!.value,
      description: this.editForm.get(['description'])!.value,
      status: this.editForm.get(['status'])!.value,
      architectureComponentId: this.editForm.get(['architectureComponentId'])!.value,
      architectureComponentId: this.editForm.get(['architectureComponentId'])!.value,
      flowsId: this.editForm.get(['flowsId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFlow>>): void {
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
