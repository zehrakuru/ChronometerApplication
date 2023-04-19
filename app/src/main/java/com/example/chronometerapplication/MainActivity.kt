package com.example.chronometerapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import com.example.chronometerapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    var isPlay = false
    var pauseOffSet : Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnPlay.setOnClickListener {
                if(!isPlay) {
                    chronometer.base = SystemClock.elapsedRealtime() - pauseOffSet
                    chronometer.start()
                    btnPause.visibility = View.VISIBLE
                    btnPlay.setImageResource(R.drawable.stop_icon)
                    isPlay = true
                } else {
                    chronometer.base = SystemClock.elapsedRealtime()
                    pauseOffSet = 0
                    chronometer.stop()
                    btnPlay.setImageResource(R.drawable.play_icon)
                    isPlay = false
                }
            }

            btnPause.setOnClickListener {
                if(isPlay) {
                    chronometer.stop()
                    pauseOffSet = SystemClock.elapsedRealtime() - chronometer.base
                    isPlay = false
                    btnPause.setImageResource(R.drawable.play_icon)
                } else {
                    chronometer.base = SystemClock.elapsedRealtime() - pauseOffSet
                    chronometer.start()
                    btnPause.setImageResource(R.drawable.pause_icon)
                    isPlay = true

                }
            }
        }
    }
}