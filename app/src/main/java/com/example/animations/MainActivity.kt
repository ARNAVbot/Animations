package com.example.animations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.example.animations.CardFlip.CardFlipActivity
import com.example.animations.CrossafadeAnimation.CrossfadeActivity
import com.example.animations.PropertyAnimations.PropertyAnimationActivity

class MainActivity : AppCompatActivity() {

    private lateinit var startCrossfadeActivityAcb: AppCompatButton
    private lateinit var startCardFlipActivityAcb: AppCompatButton
    private lateinit var startPropertyActivity1Acb: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startCrossfadeActivityAcb = findViewById(R.id.start_crossfade_acitivty)
        startCrossfadeActivityAcb.setOnClickListener {
            val intent = Intent(this, CrossfadeActivity::class.java)
            startActivity(intent)
        }

        startCardFlipActivityAcb = findViewById(R.id.start_card_flip_acitivty)
        startCardFlipActivityAcb.setOnClickListener {
            val intent = Intent(this, CardFlipActivity::class.java)
            startActivity(intent)
        }

        startPropertyActivity1Acb = findViewById(R.id.start_property_act_1)
        startPropertyActivity1Acb.setOnClickListener {
            val intent = Intent(this, PropertyAnimationActivity::class.java)
            startActivity(intent)
        }
    }
}

