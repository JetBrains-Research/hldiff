package ru.karvozavr.hldiff.steps

import ru.karvozavr.hldiff.data.HighLevelDiff
import ru.karvozavr.hldiff.pipeline.PipelineStep

class FinalStep : PipelineStep<HighLevelDiff>() {

  override fun processData(payload: HighLevelDiff): HighLevelDiff {
    payload.highLevelEditScript.sortBy { it.node.pos }
    return payload
  }

}
