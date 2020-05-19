import { IArchitectureComponent } from 'app/shared/model/architecture-component.model';

export interface IComponentCategory {
  id?: number;
  shortLabel?: string;
  label?: string;
  longLabel?: string;
  categories?: IArchitectureComponent[];
}

export class ComponentCategory implements IComponentCategory {
  constructor(
    public id?: number,
    public shortLabel?: string,
    public label?: string,
    public longLabel?: string,
    public categories?: IArchitectureComponent[]
  ) {}
}
