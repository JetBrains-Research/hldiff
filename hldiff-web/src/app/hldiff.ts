import { Change } from "./change";

export interface HLDiff {
  id: string,
  highLevelActions: Array<Change>,
  srcBefore: string,
  srcAfter: string
}
