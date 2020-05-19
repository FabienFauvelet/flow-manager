import { IArchitectureComponent } from 'app/shared/model/architecture-component.model';
import { IFlow } from 'app/shared/model/flow.model';

export interface IProject {
  id?: number;
  name?: string;
  architectureComponents?: IArchitectureComponent[];
  flows?: IFlow[];
}

export class Project implements IProject {
  constructor(public id?: number, public name?: string, public architectureComponents?: IArchitectureComponent[], public flows?: IFlow[]) {}
}
