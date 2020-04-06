export interface Change {
  id: number,
  nodeId: number,
  type: string,
  name: string,
  groupedActions: Array<any>,
  startPosition: number,
  endPosition: number,
  startPositionAfter: number,
  endPositionAfter: number
}
