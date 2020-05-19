export interface IComponentCategory {
  id?: number;
  shortLabel?: string;
  label?: string;
  longLabel?: string;
}

export class ComponentCategory implements IComponentCategory {
  constructor(public id?: number, public shortLabel?: string, public label?: string, public longLabel?: string) {}
}
