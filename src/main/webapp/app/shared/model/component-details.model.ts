export interface IComponentDetails {
  id?: number;
  server?: string;
  port?: string;
  url?: string;
  environmentId?: number;
  architectureComponentId?: number;
  detailsByEnvironementId?: number;
  componentDetailsId?: number;
}

export class ComponentDetails implements IComponentDetails {
  constructor(
    public id?: number,
    public server?: string,
    public port?: string,
    public url?: string,
    public environmentId?: number,
    public architectureComponentId?: number,
    public detailsByEnvironementId?: number,
    public componentDetailsId?: number
  ) {}
}
