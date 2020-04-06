import { Component, Input, OnInit } from '@angular/core';
import { HLDiff } from "../hldiff";
import { SourceCodeType } from "./source-code.type";
import { Change } from "../change";
import { CodeFragment } from "../code-fragment";

@Component({
  selector: 'app-source-code',
  templateUrl: './source-code.component.html',
  styleUrls: ['./source-code.component.scss']
})
export class SourceCodeComponent implements OnInit {

  @Input()
  diff: HLDiff;

  @Input()
  type: SourceCodeType;

  rootCodeFragment: CodeFragment;

  private getStartPosition(change: Change) {
    return this.type === SourceCodeType.BEFORE_CHANGES ? change.startPosition : change.startPositionAfter;
  }

  private getEndPosition(change: Change) {
    return this.type === SourceCodeType.BEFORE_CHANGES ? change.endPosition : change.endPositionAfter;
  }

  constructor() {
  }

  ngOnInit(): void {
    const src = this.type === SourceCodeType.BEFORE_CHANGES
      ? this.diff.srcBefore
      : this.diff.srcAfter;

    const changes = this.type === SourceCodeType.BEFORE_CHANGES
      ? this.diff.highLevelActions
        .filter(change => change.startPosition !== undefined)
        .sort((a, b) => b.startPosition - a.startPosition)
      : this.diff.highLevelActions
        .filter(change => change.startPositionAfter !== undefined)
        .sort((a, b) => b.startPositionAfter - a.startPositionAfter);

    this.rootCodeFragment = <CodeFragment>{ children: [], startPosition: 0, endPosition: src.length };
    let [changeIdx, lastPosition] = this.buildCodeFragmentsRecursive(this.rootCodeFragment, 0, 0, changes, src);
    console.assert(changeIdx === changes.length, "ChangeId after tree build should be changes.length");
  }

  /**
   * Build tree of code fragments
   *
   * @param root Root fragment
   * @param lastPosition last position
   * @param currentChange current change index
   * @param changes sorted by (start, end) array of changes
   * @param src source code
   *
   * @return (currentChange, lastPosition)
   */
  private buildCodeFragmentsRecursive(root: CodeFragment, lastPosition: number, currentChange: number, changes: Array<Change>, src: string): [number, number] {
    while (currentChange < changes.length) {
      const change = changes[currentChange];
      let startPosition = this.getStartPosition(change);
      let endPosition = this.getEndPosition(change);

      if (endPosition < root.endPosition) {
        // Add source code fragment before this change
        if (lastPosition < startPosition) {
          root.children.push(<CodeFragment>{
            startPosition: lastPosition,
            endPosition: startPosition,
            code: src.slice(lastPosition, startPosition)
          });
        }

        // Add this change fragment
        let fragment: CodeFragment = <CodeFragment>{
          startPosition: startPosition,
          endPosition: endPosition,
          children: [],
          change: change
        };
        root.children.push(fragment);

        // Add children fragments
        lastPosition = startPosition;
        [currentChange, lastPosition] = this.buildCodeFragmentsRecursive(
          fragment,
          lastPosition,
          currentChange + 1,
          changes,
          src
        );

        // Add fragment after last child
        if (lastPosition < endPosition) {
          fragment.children.push(<CodeFragment>{
            startPosition: lastPosition,
            endPosition: endPosition,
            code: src.slice(lastPosition, endPosition)
          });
        }
      } else {
        return [currentChange, lastPosition];
      }
    }

    return [currentChange, lastPosition]
  }

}
