import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FlowManagerTestModule } from '../../../test.module';
import { ComponentCategoryDetailComponent } from 'app/entities/component-category/component-category-detail.component';
import { ComponentCategory } from 'app/shared/model/component-category.model';

describe('Component Tests', () => {
  describe('ComponentCategory Management Detail Component', () => {
    let comp: ComponentCategoryDetailComponent;
    let fixture: ComponentFixture<ComponentCategoryDetailComponent>;
    const route = ({ data: of({ componentCategory: new ComponentCategory(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FlowManagerTestModule],
        declarations: [ComponentCategoryDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ComponentCategoryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ComponentCategoryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load componentCategory on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.componentCategory).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
