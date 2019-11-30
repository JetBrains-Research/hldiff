package ru.karvozavr.hldiff.pipeline

import java.util.*

@FunctionalInterface
abstract class PipelineStep<PayloadT> {

    private var result: Optional<PayloadT> = Optional.empty()

    protected abstract fun processData(payload: PayloadT): PayloadT

    fun apply(payload: PayloadT): PayloadT {
        result = Optional.of(processData(payload))
        return result.get()
    }

    fun getResult(): Optional<PayloadT> {
        return result
    }
}

//typealias PipelineStep<PayloadT> = (PayloadT) -> PayloadT
