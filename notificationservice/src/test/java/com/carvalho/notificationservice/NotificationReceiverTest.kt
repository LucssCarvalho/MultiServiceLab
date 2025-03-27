package com.carvalho.notificationservice

import android.content.Context
import android.content.Intent
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.spyk
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class NotificationReceiverTest : BaseTest() {

    private lateinit var context: Context
    private lateinit var receiver: NotificationReceiver

    @Before
    fun setUp() {
        context = mockk(relaxed = true)
        receiver = spyk(object : NotificationReceiver() {
            override fun createServiceIntent(context: Context, title: String?, message: String?): Intent {
                val mockedIntent = mockk<Intent>(relaxed = true)
                every { mockedIntent.getStringExtra("title") } returns title
                every { mockedIntent.getStringExtra("message") } returns message
                return mockedIntent
            }
        })
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `onReceive deve iniciar o NotificationService com extras corretos`() {
        val incomingIntent = mockk<Intent>(relaxed = true)
        every { incomingIntent.getStringExtra("title") } returns "Título Teste"
        every { incomingIntent.getStringExtra("message") } returns "Mensagem Teste"

        every { context.startService(any()) } returns null

        receiver.onReceive(context, incomingIntent)

        verify(exactly = 1) { context.startService(any()) }
    }
}
