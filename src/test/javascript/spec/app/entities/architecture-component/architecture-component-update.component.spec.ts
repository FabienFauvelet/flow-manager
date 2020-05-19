import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FlowManagerTestModule } from '../../../test.module';
import { ArchitectureComponentUpdateComponent } from 'app/entities/architecture-component/architecture-component-update.component';
import { ArchitectureComponentService } from 'app/entities/architecture-component/architecture-component.service';
import { ArchitectureComponent } from 'app/shared/model/architecture-component.model';

describe('Component Tests', () => {
  describe('ArchitectureComponent Management Update Component', () => {
    let comp: ArchitectureComponentUpdateComponent;
    let fixture: ComponentFixture<ArchitectureComponentUpdateComponent>;
    let service: ArchitectureComponentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FlowManagerTestModule],
        declarations: [ArchitectureComponentUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ArchitectureComponentUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ArchitectureComponentUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ArchitectureComponentService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ArchitectureComponent(123);
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
        const entity = new ArchitectureComponent();
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
