export interface IComponentDetails {
  id?: number;
  server?: string;
  port?: string;
  url?: string;
  environmentId?: number;
  componentId?: number;
  architectureComponentId?: number;
  environmentId?: number;
}

export class ComponentDetails implements IComponentDetails {
  constructor(
    public id?: number,
    public server?: string,
    public port?: string,
    public url?: string,
    public environmentId?: number,
    public componentId?: number,
    public architectureComponentId?: number,
    public environmentId?: number
  ) {}
}
