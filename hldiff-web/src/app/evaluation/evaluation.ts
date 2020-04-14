export interface Evaluation {
  author: string;
  diffId: string;
  actions: Array<ChangeActionEvaluation>;
  comment: string;
}

export interface ChangeActionEvaluation {
  actionID: number;
  qualityValue: string;
  comment: string;
}
