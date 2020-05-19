export interface IFlow {
  id?: number;
  description?: string;
  status?: boolean;
  architectureComponentId?: number;
  architectureComponentId?: number;
  flowsId?: number;
}

export class Flow implements IFlow {
  constructor(
    public id?: number,
    public description?: string,
    public status?: boolean,
    public architectureComponentId?: number,
    public architectureComponentId?: number,
    public flowsId?: number
  ) {
    this.status = this.status || false;
  }
}
