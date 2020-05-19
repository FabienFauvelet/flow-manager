import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { FlowManagerTestModule } from '../../../test.module';
import { ComponentDetailsComponent } from 'app/entities/component-details/component-details.component';
import { ComponentDetailsService } from 'app/entities/component-details/component-details.service';
import { ComponentDetails } from 'app/shared/model/component-details.model';

describe('Component Tests', () => {
  describe('ComponentDetails Management Component', () => {
    let comp: ComponentDetailsComponent;
    let fixture: ComponentFixture<ComponentDetailsComponent>;
    let service: ComponentDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FlowManagerTestModule],
        declarations: [ComponentDetailsComponent],
      })
        .overrideTemplate(ComponentDetailsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ComponentDetailsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ComponentDetailsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ComponentDetails(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.componentDetails && comp.componentDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
