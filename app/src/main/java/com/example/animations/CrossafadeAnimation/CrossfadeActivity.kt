package com.example.animations.CrossafadeAnimation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.animations.R

class CrossfadeActivity : AppCompatActivity() {

    private lateinit var parentView: View
    private lateinit var loadingView: View
    private lateinit var startAnimationButton : AppCompatButton
    private var shortAnimationDuration: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crossfade)

        parentView = findViewById(R.id.content)
        loadingView = findViewById(R.id.loading_spinner)
        startAnimationButton = findViewById(R.id.acb_start_animation)
        startAnimationButton.setOnClickListener {
            crossfade()
        }

        // Initially hide the content view.
        parentView.visibility = View.GONE

        // Retrieve and cache the system's default "short" animation time.
        // The following duration could be anything
        shortAnimationDuration = resources.getInteger(android.R.integer.config_longAnimTime)
//        shortAnimationDuration = 1000
    }

    private fun crossfade() {
        parentView.apply {
            // Set the content view to 0% opacity but visible, so that it is visible
            // (but fully transparent) during the animation.
            alpha = 0f
            visibility = View.VISIBLE

            // Animate the content view to 100% opacity, and clear any animation
            // listener set on the view.
            animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration.toLong())
                .setListener(null)
        }
        // Animate the loading view to 0% opacity. After the animation ends,
        // set its visibility to GONE as an optimization step (it won't
        // participate in layout passes, etc.)
        loadingView.animate()
            .alpha(0f)
            .setDuration(shortAnimationDuration.toLong())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    loadingView.visibility = View.GONE
                }
            })
    }
}