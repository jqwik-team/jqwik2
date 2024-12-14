package net.jqwik2.kotlin.core.api

import kotlin.test.Test

class GlobalsTests {

    @Test
    fun `jqwikVersion() is 2`() {
        assert(jqwikVersion() == "2")
    }
}