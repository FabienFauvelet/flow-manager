import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { FlowManagerTestModule } from '../../../test.module';
import { FlowComponent } from 'app/entities/flow/flow.component';
import { FlowService } from 'app/entities/flow/flow.service';
import { Flow } from 'app/shared/model/flow.model';

describe('Component Tests', () => {
  describe('Flow Management Component', () => {
    let comp: FlowComponent;
    let fixture: ComponentFixture<FlowComponent>;
    let service: FlowService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FlowManagerTestModule],
        declarations: [FlowComponent],
      })
        .overrideTemplate(FlowComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FlowComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FlowService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Flow(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.flows && comp.flows[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
