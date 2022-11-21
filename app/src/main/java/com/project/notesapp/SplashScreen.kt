package com.project.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        supportActionBar?.hide() //hide actionbar

        //Running the splash Screen
        Handler(Looper.getMainLooper()). postDelayed ( Runnable {
            // code
            startActivity ( Intent ( this , MainActivity :: class.java ) )
        }, 2000 )
    }
}