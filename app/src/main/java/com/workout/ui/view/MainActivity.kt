package com.workout.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.workout.R
import com.workout.ui.viewmodel.WorkoutSessionViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: WorkoutSessionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
    }

    private fun initUi() {
        viewModel = ViewModelProvider(this).get(WorkoutSessionViewModel::class.java)
        viewModel.remainingTime.observe(this) {
            textView_timer.text = (it / 1000).toString()
        }
        viewModel.caption.observe(this) {
            textView_caption.text = it
        }

        button_toggle_running.setOnClickListener {
            if(button_toggle_running.text.toString() == "Pause"){
                button_toggle_running.text = "Resume"
            }else{
                button_toggle_running.text = "Pause"
            }
            viewModel.toggleTimer()
        }

    }
}