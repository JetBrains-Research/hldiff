package ru.karvozavr.hldiff.data

import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class LowLevelActionDTO(val nodeId: Int, val type: String, val name: String, val parent: Int)

@Serializable
data class HighLevelActionDTO(val nodeId: Int,
                              val type: String,
                              val name: String,
                              val groupedActions: List<LowLevelActionDTO>,
                              val startPosition: Int,
                              val endPosition: Int)

@Serializable
data class HighLevelDiffDTO(val highLevelActions: List<HighLevelActionDTO>)