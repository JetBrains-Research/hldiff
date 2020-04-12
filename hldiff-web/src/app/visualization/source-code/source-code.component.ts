import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { HLDiff } from '../../hldiff';
import { SourceCodeType } from './source-code-type';
import { Change } from '../../change';
import { CodeFragment } from '../../code-fragment';

@Component({
  selector: 'app-source-code',
  templateUrl: './source-code.component.html',
  styleUrls: ['./source-code.component.scss']
})
export class SourceCodeComponent implements OnInit, OnChanges {

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
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.onDataLoaded(this.diff);
  }

  private onDataLoaded(diff: HLDiff) {
    const src = this.type === SourceCodeType.BEFORE_CHANGES
      ? diff.srcBefore
      : diff.srcAfter;
    console.log(this.type === SourceCodeType.BEFORE_CHANGES ? 'BEFORE' : 'AFTER');
    const changes = (this.type === SourceCodeType.BEFORE_CHANGES
        ? diff.highLevelActions
          .filter(change => ['update', 'delete', 'move'].lastIndexOf(change.type) >= 0)
        : diff.highLevelActions
          .filter(change => ['update', 'add', 'move'].lastIndexOf(change.type) >= 0)
    ).sort(this.changeCompareFunction());

    this.rootCodeFragment = ({ children: [], startPosition: 0, endPosition: src.length } as CodeFragment);
    const [changeIdx, lastPosition] = this.buildCodeFragmentsRecursive(this.rootCodeFragment, 0, 0, changes, src);

    if (lastPosition < src.length) {
      this.rootCodeFragment.children.push({
        startPosition: lastPosition,
        endPosition: src.length,
        code: src.slice(lastPosition, src.length)
      } as CodeFragment);
    }

    console.log(changes.length);
    console.assert(changeIdx === changes.length, 'ChangeId after tree build should be changes.length');
  }

  private changeCompareFunction() {
    return (a, b) => {
      const delta = this.getStartPosition(a) - this.getStartPosition(b);
      return delta === 0 ? this.getEndPosition(b) - this.getEndPosition(a) : delta;
    };
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
  private buildCodeFragmentsRecursive(
    root: CodeFragment, lastPosition: number, currentChange: number, changes: Array<Change>, src: string): [number, number] {
    while (currentChange < changes.length) {
      const change = changes[currentChange];
      const startPosition = this.getStartPosition(change);
      const endPosition = this.getEndPosition(change);

      if (endPosition <= root.endPosition) {
        // Add source code fragment before this change
        if (lastPosition < startPosition) {
          root.children.push({
            startPosition: lastPosition,
            endPosition: startPosition,
            code: src.slice(lastPosition, startPosition)
          } as CodeFragment);
        }

        // Add this change fragment
        const fragment: CodeFragment = {
          startPosition,
          endPosition,
          children: [],
          change
        } as CodeFragment;
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
          fragment.children.push({
            startPosition: lastPosition,
            endPosition,
            code: src.slice(lastPosition, endPosition)
          } as CodeFragment);
        }

        lastPosition = endPosition;
      } else {
        return [currentChange, lastPosition];
      }
    }

    return [currentChange, lastPosition];
  }
}
