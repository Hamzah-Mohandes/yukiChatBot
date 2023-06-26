package com.example.yuki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alan.alansdk.AlanCallback
import com.alan.alansdk.AlanConfig
import com.alan.alansdk.button.AlanButton
import com.alan.alansdk.events.EventCommand
import org.json.JSONException
/// Add AlanButton variable
var alanButton: AlanButton? = null
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




/// Define the project key
        val config = AlanConfig.builder().setProjectId("0206003d8bb91500bce7b65777a76bea2e956eca572e1d8b807a3e2338fdd0dc/stage").build()
        alanButton = findViewById(R.id.alan_button)
        alanButton?.initWithConfig(config)

        val alanCallback: AlanCallback = object : AlanCallback() {
            /// Handle commands from Alan AI Studio
            override fun onCommand(eventCommand: EventCommand) {
                try {
                    val command = eventCommand.data
                    val commandName = command.getJSONObject("data").getString("command")
                    Log.d("AlanButton", "onCommand: commandName: $commandName")
                } catch (e: JSONException) {
                    e.message?.let { Log.e("AlanButton", it) }
                }
            }
        };

/// Register callbacks
        alanButton?.registerCallback(alanCallback);





    }
}