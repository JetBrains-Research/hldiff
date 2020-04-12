import { Change } from './change';

export interface CodeFragment {
  change: Change | undefined;
  code: string;
  children: Array<CodeFragment>;
  startPosition: number;
  endPosition: number;
}
