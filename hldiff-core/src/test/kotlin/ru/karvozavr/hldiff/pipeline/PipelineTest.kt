package ru.karvozavr.hldiff.pipeline

import org.junit.Test

import org.junit.Assert.*


class PipelineTest {

    @Test
    fun testSimplePipeline() {
        val step1 = object : PipelineStep<Int>() {
            override fun processData(payload: Int): Int {
                return payload + 2
            }
        }
        val step2 = object : PipelineStep<Int>() {
            override fun processData(payload: Int): Int {
                return payload * payload
            }
        }
        val pipeline = Pipeline<Int>()
                .pipe(step1)
                .pipe(step2)

        assertFalse(pipeline.getResult().isPresent)
        assertEquals(16, pipeline.apply(2))
        assertTrue(pipeline.getResult().isPresent)
        assertEquals(16, pipeline.getResult().get())
    }
}