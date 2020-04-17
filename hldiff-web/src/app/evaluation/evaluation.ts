export interface Evaluation {
  author: string;
  diffId: string;
  actionsEvaluation: Array<ChangeActionEvaluation>;
  comment: string;
}

export interface ChangeActionEvaluation {
  actionId: number;
  scoreLabel: string;
  comment: string;
}
