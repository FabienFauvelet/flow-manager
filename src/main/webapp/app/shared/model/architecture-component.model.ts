import { IComponentDetails } from 'app/shared/model/component-details.model';

export interface IArchitectureComponent {
  id?: number;
  name?: string;
  detailsByEnvironements?: IComponentDetails[];
  categoryId?: number;
  projectId?: number;
}

export class ArchitectureComponent implements IArchitectureComponent {
  constructor(
    public id?: number,
    public name?: string,
    public detailsByEnvironements?: IComponentDetails[],
    public categoryId?: number,
    public projectId?: number
  ) {}
}
