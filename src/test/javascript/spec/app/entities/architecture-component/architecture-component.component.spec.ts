import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { FlowManagerTestModule } from '../../../test.module';
import { ArchitectureComponentComponent } from 'app/entities/architecture-component/architecture-component.component';
import { ArchitectureComponentService } from 'app/entities/architecture-component/architecture-component.service';
import { ArchitectureComponent } from 'app/shared/model/architecture-component.model';

describe('Component Tests', () => {
  describe('ArchitectureComponent Management Component', () => {
    let comp: ArchitectureComponentComponent;
    let fixture: ComponentFixture<ArchitectureComponentComponent>;
    let service: ArchitectureComponentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FlowManagerTestModule],
        declarations: [ArchitectureComponentComponent],
      })
        .overrideTemplate(ArchitectureComponentComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ArchitectureComponentComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ArchitectureComponentService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ArchitectureComponent(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.architectureComponents && comp.architectureComponents[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
