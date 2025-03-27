package com.carvalho.multiservicelab

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.carvalho.multiservicelab.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.notificationButton.setOnClickListener {
            Log.d("AppA", "Botão clicado")

            val serviceIntent = Intent().apply {
                setClassName(
                    "com.carvalho.notificationservice",
                    "com.carvalho.notificationservice.NotificationService"
                )
                putExtra("title", "Inicialização")
                putExtra("message", "Ativando app NotificationService")
            }

            try {
                applicationContext.startService(serviceIntent)
                Log.d("AppA", "Service iniciado")
            } catch (e: Exception) {
                Log.e("AppA", "Erro ao iniciar service: ${e.message}")
            }

            val broadcastIntent =
                Intent("com.carvalho.notificationservice.SHOW_NOTIFICATION").apply {
                    setPackage("com.carvalho.notificationservice")
                    putExtra("title", "DemoApp")
                    putExtra("message", "Notification Teste")
                }
            sendBroadcast(broadcastIntent)
            Log.d("AppA", "Broadcast enviado")
        }


        binding.beepButton.setOnClickListener {

        }

        binding.metricButton.setOnClickListener {

        }

        binding.localStorageButton.setOnClickListener {

        }
    }
}
