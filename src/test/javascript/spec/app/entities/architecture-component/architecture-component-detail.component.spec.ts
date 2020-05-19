import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FlowManagerTestModule } from '../../../test.module';
import { ArchitectureComponentDetailComponent } from 'app/entities/architecture-component/architecture-component-detail.component';
import { ArchitectureComponent } from 'app/shared/model/architecture-component.model';

describe('Component Tests', () => {
  describe('ArchitectureComponent Management Detail Component', () => {
    let comp: ArchitectureComponentDetailComponent;
    let fixture: ComponentFixture<ArchitectureComponentDetailComponent>;
    const route = ({ data: of({ architectureComponent: new ArchitectureComponent(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FlowManagerTestModule],
        declarations: [ArchitectureComponentDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ArchitectureComponentDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ArchitectureComponentDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load architectureComponent on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.architectureComponent).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
