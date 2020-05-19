import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEnvironment, Environment } from 'app/shared/model/environment.model';
import { EnvironmentService } from './environment.service';

@Component({
  selector: 'jhi-environment-update',
  templateUrl: './environment-update.component.html',
})
export class EnvironmentUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    shortLabel: [],
    label: [],
    longLabel: [],
  });

  constructor(protected environmentService: EnvironmentService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ environment }) => {
      this.updateForm(environment);
    });
  }

  updateForm(environment: IEnvironment): void {
    this.editForm.patchValue({
      id: environment.id,
      shortLabel: environment.shortLabel,
      label: environment.label,
      longLabel: environment.longLabel,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const environment = this.createFromForm();
    if (environment.id !== undefined) {
      this.subscribeToSaveResponse(this.environmentService.update(environment));
    } else {
      this.subscribeToSaveResponse(this.environmentService.create(environment));
    }
  }

  private createFromForm(): IEnvironment {
    return {
      ...new Environment(),
      id: this.editForm.get(['id'])!.value,
      shortLabel: this.editForm.get(['shortLabel'])!.value,
      label: this.editForm.get(['label'])!.value,
      longLabel: this.editForm.get(['longLabel'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEnvironment>>): void {
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
