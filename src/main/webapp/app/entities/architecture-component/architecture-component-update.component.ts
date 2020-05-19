import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IArchitectureComponent, ArchitectureComponent } from 'app/shared/model/architecture-component.model';
import { ArchitectureComponentService } from './architecture-component.service';
import { IComponentCategory } from 'app/shared/model/component-category.model';
import { ComponentCategoryService } from 'app/entities/component-category/component-category.service';
import { IProject } from 'app/shared/model/project.model';
import { ProjectService } from 'app/entities/project/project.service';

type SelectableEntity = IComponentCategory | IProject;

@Component({
  selector: 'jhi-architecture-component-update',
  templateUrl: './architecture-component-update.component.html',
})
export class ArchitectureComponentUpdateComponent implements OnInit {
  isSaving = false;
  componentcategories: IComponentCategory[] = [];
  projects: IProject[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    componentCategoryId: [],
    componentsId: [],
  });

  constructor(
    protected architectureComponentService: ArchitectureComponentService,
    protected componentCategoryService: ComponentCategoryService,
    protected projectService: ProjectService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ architectureComponent }) => {
      this.updateForm(architectureComponent);

      this.componentCategoryService
        .query()
        .subscribe((res: HttpResponse<IComponentCategory[]>) => (this.componentcategories = res.body || []));

      this.projectService.query().subscribe((res: HttpResponse<IProject[]>) => (this.projects = res.body || []));
    });
  }

  updateForm(architectureComponent: IArchitectureComponent): void {
    this.editForm.patchValue({
      id: architectureComponent.id,
      name: architectureComponent.name,
      componentCategoryId: architectureComponent.componentCategoryId,
      componentsId: architectureComponent.componentsId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const architectureComponent = this.createFromForm();
    if (architectureComponent.id !== undefined) {
      this.subscribeToSaveResponse(this.architectureComponentService.update(architectureComponent));
    } else {
      this.subscribeToSaveResponse(this.architectureComponentService.create(architectureComponent));
    }
  }

  private createFromForm(): IArchitectureComponent {
    return {
      ...new ArchitectureComponent(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      componentCategoryId: this.editForm.get(['componentCategoryId'])!.value,
      componentsId: this.editForm.get(['componentsId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IArchitectureComponent>>): void {
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
