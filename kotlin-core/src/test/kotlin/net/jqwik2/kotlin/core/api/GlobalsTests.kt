package net.jqwik2.kotlin.core.api

import org.junit.jupiter.api.Test

class GlobalsTests {

    @Test
    fun testJqwikVersion() {
        assert(jqwikVersion() == "2")
    }
}