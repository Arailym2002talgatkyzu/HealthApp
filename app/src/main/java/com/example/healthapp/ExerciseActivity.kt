package com.example.healthapp

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.view.ActionMode
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_exercise.*
import java.util.*

class ExerciseActivity: AppCompatActivity() {
    private lateinit var player: MediaPlayer
    private lateinit var start: MediaPlayer
    private var restTimer: CountDownTimer?= null
    private var restProgress = 0
    private var exerciseno=9
    private var toSpeak=""
    lateinit var mTTS: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        setSupportActionBar(toolbar_exercise_activity)
        mTTS = TextToSpeech(applicationContext, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR){
                //if there is no error then set language
                mTTS.language = Locale.UK
            }
        })

        player = MediaPlayer.create(applicationContext, R.raw.press_start)
        player!!.isLooping = false
        start = MediaPlayer.create(applicationContext, R.raw.beep)
        start!!.isLooping = false
        val actionbar = supportActionBar
        if(actionbar != null){
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
        toolbar_exercise_activity.setNavigationOnClickListener{
            onBackPressed()
        }
        setupRestView()
    }

    override fun onDestroy() {
        if(restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }
        super.onDestroy()
    }

    private fun setRestProgressBar(){
        progress_circular.progress = restProgress
        restTimer = object : CountDownTimer(10000,1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress ++
                progress_circular.progress = 10 - restProgress
                status.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
               /* start!!.start()*/

                exerciseno++
                toSpeak = when (exerciseno) {
                    1 -> "Abdominal crunch"
                    2-> "High knees running in place"
                    3-> "Jumping jacks"
                    4-> "Lunge"
                    5-> "Plank"
                    6-> "Push up"
                    7-> "Push up and rotation"
                    8-> "Side plank"
                    9-> "Squat"
                    10-> "Step up onto chair"
                    11-> "Ic triceps dip on chair"
                    12-> "Wall sit"
                    else -> "Congratulations"
                }
                mTTS.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null)
                Toast.makeText(this@ExerciseActivity, "Start Exercising!",Toast.LENGTH_SHORT).show()
                task.text="Exercise $exerciseno \n $toSpeak"

                status.text="15"
                progress_circular.max=15
                when(exerciseno){
                    1-> exercise.setImageResource(R.drawable.ic_abdominal_crunch)
                    2-> exercise.setImageResource(R.drawable.ic_high_knees_running_in_place)
                    3-> exercise.setImageResource(R.drawable.ic_jumping_jacks)
                    4-> exercise.setImageResource(R.drawable.ic_lunge)
                    5-> exercise.setImageResource(R.drawable.ic_plank)
                    6-> exercise.setImageResource(R.drawable.ic_push_up)
                    7-> exercise.setImageResource(R.drawable.ic_push_up_and_rotation)
                    8-> exercise.setImageResource(R.drawable.ic_side_plank)
                    9-> exercise.setImageResource(R.drawable.ic_squat)
                    10-> exercise.setImageResource(R.drawable.ic_step_up_onto_chair)
                    11-> exercise.setImageResource(R.drawable.ic_triceps_dip_on_chair)
                    12-> exercise.setImageResource(R.drawable.ic_wall_sit)
                }
                  if(exerciseno<13){
                      setupActionView()
                  }
                 else{
                      Finish()
                  }


            }
        }.start()
    }

    private fun setupRestView(){

        if(restTimer != null){
            restTimer!!.cancel()
            restProgress = 0
        }
        setRestProgressBar()
    }

    private fun setupActionView(){

        if(restTimer != null){
            restTimer!!.cancel()
            restProgress = 0
        }
        setActionProgressBar()
    }

    private fun setActionProgressBar(){
        progress_circular.progress = restProgress
        restTimer = object : CountDownTimer(15000,1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress ++
                progress_circular.progress = 15 - restProgress
                status.text = (15 - restProgress).toString()
            }

            override fun onFinish() {
                player!!.start()
                exercise.setImageResource(R.drawable.health)
                task.text=" Get Ready For! "
                status.text="10"
                progress_circular.max=10
                if(exerciseno<12){
                setupRestView()}
                else{Finish()}
            }
        }.start()
    }

    fun Finish(){
        Toast.makeText(this@ExerciseActivity, "Finish Exercising!",Toast.LENGTH_SHORT).show()
        progress_circular.max=0
        status.text="0"
        task.text="CONGRATULATIONS!"
        mTTS.speak("congratulations", TextToSpeech.QUEUE_FLUSH, null)
    }


}