package ru.karvozavr.hldiff.steps

import com.github.gumtreediff.actions.EditScript
import com.github.gumtreediff.actions.model.Move
import ru.karvozavr.hldiff.actions.MoveAction
import ru.karvozavr.hldiff.data.HighLevelDiff
import ru.karvozavr.hldiff.pipeline.PipelineStep

class MoveActionExtractionStep : PipelineStep<HighLevelDiff>() {

    override fun processData(payload: HighLevelDiff): HighLevelDiff {
        return extractMoveActions(payload)
    }

    private fun extractMoveActions(diff: HighLevelDiff): HighLevelDiff {
        val actions = diff.lowLevelEditScript
        val newActions = EditScript()
        val highLevelActions = diff.highLevelEditScript

        for (action in actions) {
            if (action is Move) {
                val moveAction = MoveAction(node = action.node, label = action.node.type.toString(), newParent = action.parent, position = action.position)
                highLevelActions.add(moveAction)
            } else {
                newActions.add(action)
            }
        }

        return HighLevelDiff(
                treeBefore = diff.treeBefore,
                treeAfter = diff.treeAfter,
                lowLevelEditScript = newActions,
                highLevelEditScript = highLevelActions,
                mappings = diff.mappings)
    }
}