package com.workout.ui.viewmodel

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.workout.data.WorkoutSession

class WorkoutSessionViewModel : ViewModel() {
    val session = WorkoutSession()
    val remainingTime = MutableLiveData<Long>().apply {
        value = session.breaks[0].duration
    }
    val caption = MutableLiveData<String>().apply {
        value = session.breaks[0].caption
    }
    var indexOfBreak = 0
    var indexOfExercise = 0
    var isCurrentAnExercise = false
    var timer: CountDownTimer
    var isRunning = false

    init {
        initBreak()
        timer = createTimer(remainingTime.value as Long)
        isRunning = true
        timer.start()
    }

    private fun initBreak() {
        isCurrentAnExercise = false
        remainingTime.value = session.breaks[indexOfBreak].duration
        caption.value = session.breaks[indexOfBreak].caption
        indexOfBreak++
    }

    private fun initExercise() {
        isCurrentAnExercise = true
        remainingTime.value = session.exercises[indexOfExercise].duration
        caption.value = session.exercises[indexOfExercise].caption
        indexOfExercise++
    }

    fun toggleTimer() {
        if(isRunning){
            pauseTimer()
            isRunning = false
        }else{
            continueTimer()
            isRunning = true
        }
    }

    fun pauseTimer() {
        timer.cancel()
    }

    fun continueTimer() {
        timer = createTimer(remainingTime.value as Long)
        timer.start()
    }

    private fun createTimer(duration: Long): CountDownTimer {
        return object : CountDownTimer(duration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                onTimerTick(millisUntilFinished)
            }

            override fun onFinish() {
                onTimerFinish()
            }
        }
    }

    private fun onTimerTick(millisUntilFinished: Long) {
        remainingTime.value = millisUntilFinished
    }

    private fun onTimerFinish() {
        remainingTime.value = 0
        if(indexOfExercise == session.exercises.size){
            Log.i("Workout", "Session finished")
            // TODO: start a GIF as a celebration in another activity here
            return;
        }
        next()
    }

    private fun next() {
        if(isCurrentAnExercise){
            initBreak()
        }else{
            initExercise()
        }
        continueTimer()
    }
}