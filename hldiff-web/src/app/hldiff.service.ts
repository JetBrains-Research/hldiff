import { Injectable } from '@angular/core';
import { HLDiff } from './hldiff'
import { Observable, of } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HLDiffService {

  constructor() {
  }

  private diff: HLDiff = <HLDiff>{
    id: "42",
    highLevelActions: [
      {
        id: 0,
        nodeId: 58,
        type: "move",
        name: "Move MethodInvocation to VariableDeclarationFragment at position 1",
        groupedActions: [],
        startPosition: 289,
        endPosition: 321,
        startPositionAfter: 298,
        endPositionAfter: 330
      },
      {
        id: 1,
        nodeId: 61,
        type: "add",
        name: "Insert VariableDeclarationStatement Partial to Block at position 0",
        groupedActions: [],
        startPosition: 282,
        endPosition: 331,
        startPositionAfter: 282,
        endPositionAfter: 331
      }, {
        id: 2,
        nodeId: 72,
        type: "add",
        name: "Insert IfStatement to Block at position 1",
        groupedActions: [],
        startPosition: 341,
        endPosition: 433,
        startPositionAfter: 341,
        endPositionAfter: 433
      }, {
        id: 3,
        nodeId: 59,
        type: "update",
        name: "Update MethodInvocation with\n\t\tINS SimpleName: decorated to MethodInvocation at 1\n",
        groupedActions: [],
        startPosition: 281,
        endPosition: 322,
        startPositionAfter: 443,
        endPositionAfter: 461
      }
    ],
    srcBefore: "class Foo {\n" +
      "\n" +
      "    public int taskDecorator(int a, int b) {\n" +
      "        return 0;\n" +
      "    }\n" +
      "\n" +
      "    public void execute(int a) {\n" +
      "\n" +
      "    }\n" +
      "\n" +
      "    public void decoratedTaskMap(int a, int b, int c) {\n" +
      "\n" +
      "    }\n" +
      "\n" +
      "    int decorate = 0;\n" +
      "    int command = 0;\n" +
      "    int put = 0;\n" +
      "\n" +
      "    public void foo() {\n" +
      "        execute(taskDecorator(decorate, command));\n" +
      "    }\n" +
      "}\n",
    srcAfter: "class Foo {\n" +
      "\n" +
      "    public int taskDecorator(int a, int b) {\n" +
      "        return 0;\n" +
      "    }\n" +
      "\n" +
      "    public void execute(int a) {\n" +
      "\n" +
      "    }\n" +
      "\n" +
      "    public void decoratedTaskMap(int a, int b, int c) {\n" +
      "\n" +
      "    }\n" +
      "\n" +
      "    int decorate = 0;\n" +
      "    int command = 0;\n" +
      "    int put = 0;\n" +
      "\n" +
      "\n" +
      "    public void foo() {\n" +
      "        int decorated = taskDecorator(decorate, command);\n" +
      "\n" +
      "        if (decorated != command) {\n" +
      "            decoratedTaskMap(put, decorated, command);\n" +
      "        }\n" +
      "\n" +
      "        execute(decorated);\n" +
      "    }\n" +
      "}"
  };

  getDiffById(id: string): Observable<HLDiff> {
    return of(this.diff)
  }
}
