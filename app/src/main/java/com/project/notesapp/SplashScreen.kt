package com.project.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        supportActionBar?.hide() //hide actionbar

        var bottom : Animation = AnimationUtils.loadAnimation(this,R.anim.from_bottom)
        var img:ImageView=findViewById(R.id.splashImg)
        img.setAnimation(bottom)
        //Running the splash Screen
        Handler(Looper.getMainLooper()). postDelayed ( Runnable {
            // code
            startActivity ( Intent ( this , MainActivity :: class.java ) )
        }, 2000 )
    }
}