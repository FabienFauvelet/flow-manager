import { IComponentDetails } from 'app/shared/model/component-details.model';

export interface IEnvironment {
  id?: number;
  shortLabel?: string;
  label?: string;
  longLabel?: string;
  componentDetails?: IComponentDetails[];
  environments?: IComponentDetails[];
}

export class Environment implements IEnvironment {
  constructor(
    public id?: number,
    public shortLabel?: string,
    public label?: string,
    public longLabel?: string,
    public componentDetails?: IComponentDetails[],
    public environments?: IComponentDetails[]
  ) {}
}
