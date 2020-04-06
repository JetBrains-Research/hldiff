import { Change } from "./change";

interface HLDiff {
  id: string,
  highLevelActions: Array<Change>,
  srcBefore: string,
  srcAfter: string
}
