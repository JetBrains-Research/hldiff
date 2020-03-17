package ru.karvozavr.hldiff.data

import com.github.gumtreediff.actions.model.Move
import com.github.gumtreediff.tree.TreeContext
import kotlinx.serialization.json.*
import ru.karvozavr.hldiff.actions.MoveAction

class HLDiffFormatter(private val hldiff: HighLevelDiff, private val treeContext: TreeContext, private val codeBefore: String, private val codeAfter: String) {

    fun formatJSON(): String {
        val json = Json(JsonConfiguration.Stable)
        val highLevelActions: List<HighLevelActionDTO> = hldiff.highLevelEditScript.mapIndexed { idx, it ->
            HighLevelActionDTO(
                    id = idx,
                    nodeId = it.node.id,
                    name = it.format(treeContext, codeBefore, codeAfter),
                    type = it.getActionType(),
                    groupedActions = emptyList(), // TODO get grouped actions
                    startPosition = it.node.pos,
                    endPosition = it.node.endPos,
                    startPositionAfter = hldiff.mappings.getDst(it.node)?.pos ?: it.node.pos,
                    endPositionAfter = hldiff.mappings.getDst(it.node)?.endPos ?: it.node.endPos
            )
        }

        val hlDiffData = HighLevelDiffDTO(highLevelActions)

        return json.toJson(HighLevelDiffDTO.serializer(), hlDiffData).toString()
    }

    fun formatText(): String {
        val textBuilder = StringBuilder()
        hldiff.highLevelEditScript.forEach {
            textBuilder.append(it.format(treeContext, codeBefore, codeAfter))
        }
        return textBuilder.toString()
    }
}