import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FlowManagerTestModule } from '../../../test.module';
import { ComponentDetailsUpdateComponent } from 'app/entities/component-details/component-details-update.component';
import { ComponentDetailsService } from 'app/entities/component-details/component-details.service';
import { ComponentDetails } from 'app/shared/model/component-details.model';

describe('Component Tests', () => {
  describe('ComponentDetails Management Update Component', () => {
    let comp: ComponentDetailsUpdateComponent;
    let fixture: ComponentFixture<ComponentDetailsUpdateComponent>;
    let service: ComponentDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FlowManagerTestModule],
        declarations: [ComponentDetailsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ComponentDetailsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ComponentDetailsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ComponentDetailsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ComponentDetails(123);
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
        const entity = new ComponentDetails();
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
