package com.frozen.dailys

import com.frozen.dailys.util.extensions.md5
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun md5() {
        var md5 = "123".md5(true)
        print(md5)
    }
}
