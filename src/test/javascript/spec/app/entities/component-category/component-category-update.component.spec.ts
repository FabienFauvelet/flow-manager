import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FlowManagerTestModule } from '../../../test.module';
import { ComponentCategoryUpdateComponent } from 'app/entities/component-category/component-category-update.component';
import { ComponentCategoryService } from 'app/entities/component-category/component-category.service';
import { ComponentCategory } from 'app/shared/model/component-category.model';

describe('Component Tests', () => {
  describe('ComponentCategory Management Update Component', () => {
    let comp: ComponentCategoryUpdateComponent;
    let fixture: ComponentFixture<ComponentCategoryUpdateComponent>;
    let service: ComponentCategoryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FlowManagerTestModule],
        declarations: [ComponentCategoryUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ComponentCategoryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ComponentCategoryUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ComponentCategoryService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ComponentCategory(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ComponentCategory();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
