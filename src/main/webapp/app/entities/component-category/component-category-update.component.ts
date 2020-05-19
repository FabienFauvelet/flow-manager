import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IComponentCategory, ComponentCategory } from 'app/shared/model/component-category.model';
import { ComponentCategoryService } from './component-category.service';

@Component({
  selector: 'jhi-component-category-update',
  templateUrl: './component-category-update.component.html',
})
export class ComponentCategoryUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    shortLabel: [],
    label: [],
    longLabel: [],
  });

  constructor(
    protected componentCategoryService: ComponentCategoryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ componentCategory }) => {
      this.updateForm(componentCategory);
    });
  }

  updateForm(componentCategory: IComponentCategory): void {
    this.editForm.patchValue({
      id: componentCategory.id,
      shortLabel: componentCategory.shortLabel,
      label: componentCategory.label,
      longLabel: componentCategory.longLabel,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const componentCategory = this.createFromForm();
    if (componentCategory.id !== undefined) {
      this.subscribeToSaveResponse(this.componentCategoryService.update(componentCategory));
    } else {
      this.subscribeToSaveResponse(this.componentCategoryService.create(componentCategory));
    }
  }

  private createFromForm(): IComponentCategory {
    return {
      ...new ComponentCategory(),
      id: this.editForm.get(['id'])!.value,
      shortLabel: this.editForm.get(['shortLabel'])!.value,
      label: this.editForm.get(['label'])!.value,
      longLabel: this.editForm.get(['longLabel'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IComponentCategory>>): void {
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
}
