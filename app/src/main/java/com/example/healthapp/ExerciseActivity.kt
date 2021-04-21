package com.example.healthapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.ActionMode
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_exercise.*
class ExerciseActivity: AppCompatActivity() {
    private var restTimer: CountDownTimer?= null
    private var restProgress = 0
    private var exerciseno=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        setSupportActionBar(toolbar_exercise_activity)
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
                exerciseno++
                Toast.makeText(this@ExerciseActivity, "Start Exercising!",Toast.LENGTH_SHORT).show()
                task.text="Exercise $exerciseno"

                status.text="30"
                progress_circular.max=30
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
        restTimer = object : CountDownTimer(30000,1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress ++
                progress_circular.progress = 30 - restProgress
                status.text = (30 - restProgress).toString()
            }

            override fun onFinish() {
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
    }


}