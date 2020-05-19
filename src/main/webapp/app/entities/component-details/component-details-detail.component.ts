import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IComponentDetails } from 'app/shared/model/component-details.model';

@Component({
  selector: 'jhi-component-details-detail',
  templateUrl: './component-details-detail.component.html',
})
export class ComponentDetailsDetailComponent implements OnInit {
  componentDetails: IComponentDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ componentDetails }) => (this.componentDetails = componentDetails));
  }

  previousState(): void {
    window.history.back();
  }
}
