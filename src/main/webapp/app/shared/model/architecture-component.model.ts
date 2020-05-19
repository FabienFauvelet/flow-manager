import { IComponentDetails } from 'app/shared/model/component-details.model';
import { IFlow } from 'app/shared/model/flow.model';

export interface IArchitectureComponent {
  id?: number;
  name?: string;
  componentDetails?: IComponentDetails[];
  componentCategoryId?: number;
  componentsId?: number;
  componentFroms?: IFlow[];
  componentTos?: IFlow[];
  components?: IComponentDetails[];
}

export class ArchitectureComponent implements IArchitectureComponent {
  constructor(
    public id?: number,
    public name?: string,
    public componentDetails?: IComponentDetails[],
    public componentCategoryId?: number,
    public componentsId?: number,
    public componentFroms?: IFlow[],
    public componentTos?: IFlow[],
    public components?: IComponentDetails[]
  ) {}
}
