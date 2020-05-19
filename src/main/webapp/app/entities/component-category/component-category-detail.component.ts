import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IComponentCategory } from 'app/shared/model/component-category.model';

@Component({
  selector: 'jhi-component-category-detail',
  templateUrl: './component-category-detail.component.html',
})
export class ComponentCategoryDetailComponent implements OnInit {
  componentCategory: IComponentCategory | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ componentCategory }) => (this.componentCategory = componentCategory));
  }

  previousState(): void {
    window.history.back();
  }
}
