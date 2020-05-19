export interface IFlow {
  id?: number;
  description?: string;
  status?: boolean;
  componentFromId?: number;
  componentToId?: number;
  projectId?: number;
}

export class Flow implements IFlow {
  constructor(
    public id?: number,
    public description?: string,
    public status?: boolean,
    public componentFromId?: number,
    public componentToId?: number,
    public projectId?: number
  ) {
    this.status = this.status || false;
  }
}
