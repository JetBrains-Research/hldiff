package ru.karvozavr.hldiff.pipeline

class Pipeline<PayloadT> : PipelineStep<PayloadT>() {

    private val steps: ArrayList<PipelineStep<PayloadT>> = ArrayList()

    fun pipe(step: PipelineStep<PayloadT>): Pipeline<PayloadT> {
        steps.add(step)
        return this
    }

    override fun processData(payload: PayloadT): PayloadT {
        var result = payload
        for (step  in steps) {
            result = step.getResult().orElse(step.apply(result))
        }

        return result
    }
}