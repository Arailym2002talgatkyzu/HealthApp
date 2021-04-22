package com.example.healthapp

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var player: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*player = MediaPlayer.create(applicationContext, R.raw.press_start)
        player!!.isLooping = false*/ // Sets the player to be looping or non-looping.
       // player!!.start() // Starts Playback.
         start.setOnClickListener {
//             player!!.start()
             val intent = Intent(this, ExerciseActivity::class.java)
             startActivity(intent)
         }
    }
}