import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FlowManagerTestModule } from '../../../test.module';
import { ComponentDetailsDetailComponent } from 'app/entities/component-details/component-details-detail.component';
import { ComponentDetails } from 'app/shared/model/component-details.model';

describe('Component Tests', () => {
  describe('ComponentDetails Management Detail Component', () => {
    let comp: ComponentDetailsDetailComponent;
    let fixture: ComponentFixture<ComponentDetailsDetailComponent>;
    const route = ({ data: of({ componentDetails: new ComponentDetails(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FlowManagerTestModule],
        declarations: [ComponentDetailsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ComponentDetailsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ComponentDetailsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load componentDetails on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.componentDetails).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
