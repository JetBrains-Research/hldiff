package ru.karvozavr.hldiff.visualisation

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import ru.karvozavr.hldiff.data.HighLevelActionDTO
import ru.karvozavr.hldiff.data.HighLevelDiffDTO
import java.io.File

data class Event(val type: EventType, val action: HighLevelActionDTO, val metric: Int) {

    enum class EventType {
        OPEN_TAG,
        CLOSE_TAG
    }
}

class VisualisationGenerator {

    fun generate(jsonDiff: String, programBefore: String, programAfter: String): String {
        val json = Json(JsonConfiguration.Stable)
        val diff = json.parse(HighLevelDiffDTO.serializer(), jsonDiff)
        return generateHTML(diff, programBefore, programAfter)
    }

    private fun generateHTML(diff: HighLevelDiffDTO, programBefore: String, programAfter: String): String {
        val actionsFiltered = diff.highLevelActions

        val eventsBefore = actionsFiltered.map { Event(type = Event.EventType.OPEN_TAG, action = it, metric = it.startPosition) }.toMutableList()
        eventsBefore.addAll(actionsFiltered.map { Event(type = Event.EventType.CLOSE_TAG, action = it, metric = it.endPosition) })
        eventsBefore.sortBy { it.metric }

        val eventsAfter = actionsFiltered.map { Event(type = Event.EventType.OPEN_TAG, action = it, metric = it.startPositionAfter!!) }.toMutableList()
        eventsAfter.addAll(actionsFiltered.map { Event(type = Event.EventType.CLOSE_TAG, action = it, metric = it.endPositionAfter!!) })
        eventsAfter.sortBy { it.metric }

        val programBeforeHtml = formatBeforeProgram(eventsBefore, programBefore)
        val programAfterHtml = formatAfterProgram(eventsAfter, programAfter)
        val actionsHTML = formatActions(actionsFiltered.sortedBy { it.startPosition })

        val template = File(this.javaClass.getResource("/visualization/visual_template.html").toURI()).readText()
        return template.replace("__PROGRAM_BEFORE", programBeforeHtml)
                .replace("__ACTIONS", actionsHTML)
                .replace("__PROGRAM_AFTER", programAfterHtml)
    }

    private fun formatActions(actions: List<HighLevelActionDTO>): String {
        val builder = StringBuilder()
        for (action in actions) {
            val actionType = when (action.type) {
                "add" -> "addition"
                "move" -> "move"
                "update" -> "update"
                "delete" -> "delete"
                else -> ""
            }
            builder.append("<div class=\"change change-${actionType}\" id=\"code-change-${action.id}\">${action.name}</div>")
        }
        return builder.toString()
    }

    private fun formatAfterProgram(events: MutableList<Event>, program: String): String {
        var lastPosition = 0

        val builder = StringBuilder()

        for (event in events) {
            if (event.action.type == "delete") {
                continue
            }

            val startPosition = if (event.action.type == "move" || event.action.type == "update") event.action.startPositionAfter!! else event.action.startPosition
            val endPosition = if (event.action.type == "move" || event.action.type == "update") event.action.endPositionAfter!! else event.action.endPosition
            lastPosition = generateActionHTML(event, builder, program, lastPosition, startPosition, endPosition, event.action.id)
        }

        builder.append(program.substring(lastPosition))

        return builder.toString()
    }

    private fun formatBeforeProgram(events: MutableList<Event>, program: String): String {
        var lastPosition = 0

        val builder = StringBuilder()

        for (event in events) {
            if (event.action.type == "add") {
                continue
            }

            val startPosition = event.action.startPosition
            val endPosition = event.action.endPosition

            lastPosition = generateActionHTML(event, builder, program, lastPosition, startPosition, endPosition, event.action.id)
        }

        builder.append(program.substring(lastPosition))

        return builder.toString()
    }

    private fun generateActionHTML(event: Event, builder: StringBuilder, program: String, lastPosition: Int, startPosition: Int, endPosition: Int, id: Int): Int {
        return when (event.type) {
            Event.EventType.OPEN_TAG -> {
                builder.append(program.substring(lastPosition, startPosition))

                val actionType = when (event.action.type) {
                    "add" -> "added"
                    "move" -> "moved"
                    "update" -> "updated"
                    "delete" -> "removed"
                    else -> ""
                }

                builder.append("<span class=\"changed-code $actionType code-change-$id\">")
                startPosition
            }
            Event.EventType.CLOSE_TAG -> {
                builder.append(program.substring(lastPosition, endPosition))
                builder.append("</span>")
                endPosition
            }
        }
    }
}