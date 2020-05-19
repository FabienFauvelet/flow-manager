import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IArchitectureComponent } from 'app/shared/model/architecture-component.model';

@Component({
  selector: 'jhi-architecture-component-detail',
  templateUrl: './architecture-component-detail.component.html',
})
export class ArchitectureComponentDetailComponent implements OnInit {
  architectureComponent: IArchitectureComponent | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ architectureComponent }) => (this.architectureComponent = architectureComponent));
  }

  previousState(): void {
    window.history.back();
  }
}
