import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { FlowManagerTestModule } from '../../../test.module';
import { ComponentCategoryComponent } from 'app/entities/component-category/component-category.component';
import { ComponentCategoryService } from 'app/entities/component-category/component-category.service';
import { ComponentCategory } from 'app/shared/model/component-category.model';

describe('Component Tests', () => {
  describe('ComponentCategory Management Component', () => {
    let comp: ComponentCategoryComponent;
    let fixture: ComponentFixture<ComponentCategoryComponent>;
    let service: ComponentCategoryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FlowManagerTestModule],
        declarations: [ComponentCategoryComponent],
      })
        .overrideTemplate(ComponentCategoryComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ComponentCategoryComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ComponentCategoryService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ComponentCategory(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.componentCategories && comp.componentCategories[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
