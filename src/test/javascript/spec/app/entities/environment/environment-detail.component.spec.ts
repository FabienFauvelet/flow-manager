import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FlowManagerTestModule } from '../../../test.module';
import { EnvironmentDetailComponent } from 'app/entities/environment/environment-detail.component';
import { Environment } from 'app/shared/model/environment.model';

describe('Component Tests', () => {
  describe('Environment Management Detail Component', () => {
    let comp: EnvironmentDetailComponent;
    let fixture: ComponentFixture<EnvironmentDetailComponent>;
    const route = ({ data: of({ environment: new Environment(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FlowManagerTestModule],
        declarations: [EnvironmentDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EnvironmentDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EnvironmentDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load environment on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.environment).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
