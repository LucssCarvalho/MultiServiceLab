package com.carvalho.notificationservice

import android.util.Log
import io.mockk.every
import io.mockk.mockkStatic
import org.junit.BeforeClass

open class BaseTest {

    companion object {
        @BeforeClass
        @JvmStatic
        fun mockAndroidLog() {
            mockkStatic(Log::class)
            every { Log.d(any(), any()) } returns 0
            every { Log.i(any(), any()) } returns 0
            every { Log.e(any(), any()) } returns 0
            every { Log.v(any(), any()) } returns 0
        }
    }
}
