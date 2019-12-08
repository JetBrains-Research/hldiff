package ru.karvozavr.hldiff.steps

import com.github.gumtreediff.actions.model.Action
import com.github.gumtreediff.actions.model.Move
import ru.karvozavr.hldiff.actions.HighLevelAction
import ru.karvozavr.hldiff.data.HighLevelDiff
import ru.karvozavr.hldiff.pipeline.PipelineStep

class MoveActionExtractionStep : PipelineStep<HighLevelDiff>() {

    override fun processData(payload: HighLevelDiff): HighLevelDiff {
        return extractMoveActions(payload)
    }

    private fun extractMoveActions(diff: HighLevelDiff): HighLevelDiff {
        val actions = diff.lowLevelEditScript
        val newActions = mutableListOf<Action>()
        val highLevelActions = diff.highLevelEditScript

        for (action in actions) {
            if (action is Move) {
                val moveAction = HighLevelAction.of(action)
                highLevelActions.add(moveAction)
            } else {
                newActions.add(action)
            }
        }

        return diff.copy(
                lowLevelEditScript = newActions,
                highLevelEditScript = highLevelActions)
    }
}