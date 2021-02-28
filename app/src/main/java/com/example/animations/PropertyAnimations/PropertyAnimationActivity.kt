package com.example.animations.PropertyAnimations

import android.animation.*
import android.os.Bundle
import android.view.animation.BounceInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.animation.addListener
import com.example.animations.R


/**
//NOTE: https://guides.codepath.com/android/animations
//NOTE : View property animation is faster than ObjectAnimator
 **/

class PropertyAnimationActivity : AppCompatActivity() {

    // All the different properties that can be animated are found here : https://developer.android.com/guide/topics/graphics/prop-animation.html#views

    private lateinit var fadeOutButton: AppCompatButton
    private lateinit var shrinkAndExpandButton: AppCompatButton
    private lateinit var interpolationButton: AppCompatButton
    private lateinit var choreographButton: AppCompatButton

    private lateinit var viewPropertyAnimatorButton: AppCompatButton
    private lateinit var viewPropertyAnimator2Button: AppCompatButton

    private lateinit var animationUsingXml1Acb: AppCompatButton
    private lateinit var multiAnimationUsingXmlButton: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peoperty_animation)
        fadeOutButton = findViewById(R.id.acb_fade)
        fadeOutButton.setOnClickListener {
            val fadeAnim = ObjectAnimator.ofFloat(fadeOutButton, "alpha", 0.2f)
            fadeAnim.addListener(
                onEnd = {
                    Toast.makeText(this, "End!", Toast.LENGTH_SHORT).show();
                }
            )
            fadeAnim.start()
        }

        shrinkAndExpandButton = findViewById(R.id.acb_shrink)
        shrinkAndExpandButton.setOnClickListener {
            val scaleAnim = ObjectAnimator.ofFloat(shrinkAndExpandButton, "scaleX", 1.0f, 2.0f)
            scaleAnim.duration = 3000
            scaleAnim.repeatCount = ValueAnimator.INFINITE
            scaleAnim.repeatMode = ValueAnimator.REVERSE
            scaleAnim.start()
        }

        interpolationButton = findViewById(R.id.acb_interpolation)
        interpolationButton.setOnClickListener {
            val moveAnim: ObjectAnimator = ObjectAnimator.ofFloat(interpolationButton, "Y", 1000f)
            moveAnim.duration = 2000

            // there can be multiple other interpolators here :
//            AccelerateInterpolator	: Rate of change starts out slowly and and then accelerates
//            BounceInterpolator	: Change bounces right at the end
//            DecelerateInterpolator :	Rate of change starts out quickly and and then decelerates
//            LinearInterpolator	: Rate of change is constant throughout
            moveAnim.interpolator = BounceInterpolator()
            moveAnim.start()
        }

        choreographButton = findViewById(R.id.acb_chroegraph)
        choreographButton.setOnClickListener {
            val set = AnimatorSet()

            // we can have animations play one after the other or sequentially etc etc
            /**
            // Define first set of animations
            // Define first set of animations
            val anim1 = ObjectAnimator.ofFloat(tvLabel, "scaleX", 2.0f)
            val anim2 = ObjectAnimator.ofFloat(tvLabel, "scaleY", 2.0f)
            val set1 = AnimatorSet()
            set1.playTogether(anim1, anim2)
            // Define second set of animations
            // Define second set of animations
            val anim3: ObjectAnimator = ObjectAnimator.ofFloat(v, "X", 300)
            val anim4: ObjectAnimator = ObjectAnimator.ofFloat(v, "Y", 300)
            val set2 = AnimatorSet()
            set2.playTogether(anim3, anim4)
            // Play the animation sets one after another
            // Play the animation sets one after another
            val set3 = AnimatorSet()
            set3.playSequentially(set1, set2)
            set3.start()
             **/

            /**
            // Create two animations to play together
            ObjectAnimator bounceAnim = ...;
            ObjectAnimator squashAnim = ...;
            // Construct set 1 playing together
            AnimatorSet bouncer = new AnimatorSet();
            bouncer.play(bounceAnim).with(squashAnim);
            // Create second animation to play after
            ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(view1, "alpha", 1f, 0f);
            fadeAnim.setDuration(250);
            // Play bouncer before fade
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(bouncer).before(fadeAnim);
            animatorSet.start();

             **/

            set.playTogether(
                ObjectAnimator.ofFloat(choreographButton, "scaleX", 1.0f, 2.0f)
                    .setDuration(2000),
                ObjectAnimator.ofFloat(choreographButton, "scaleY", 1.0f, 2.0f)
                    .setDuration(2000),
                ObjectAnimator.ofObject(
                    choreographButton, "backgroundColor", ArgbEvaluator(),  /*Red*/
                    -0x7f80,  /*Blue*/-0x7f7f01
                )
                    .setDuration(2000)
            )
            set.start()
        }

        // Example of view property animation
        viewPropertyAnimatorButton = findViewById(R.id.acb_view_property_animator)
        viewPropertyAnimatorButton.setOnClickListener {
            viewPropertyAnimatorButton.animate().alpha(0.2f).xBy(-100f).yBy(100f);
        }

        viewPropertyAnimator2Button = findViewById(R.id.acb_view_property_animator_2)
        viewPropertyAnimator2Button.setOnClickListener {
            // To check what each single anumation is doing ,comment the rest and just execute the specific animation
            viewPropertyAnimator2Button.animate()
                .alpha(0.5f)
                .rotation(90f)
                .scaleX(2f)
                .xBy(100f)
                .yBy(100f)
                .setDuration(1000)
                .setStartDelay(10)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator) {
                        Toast.makeText(
                            this@PropertyAnimationActivity,
                            "Started...",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }

        // animation using xml's
        animationUsingXml1Acb = findViewById(R.id.acb_animation_xml_1)
        animationUsingXml1Acb.setOnClickListener {
            val anim = AnimatorInflater.loadAnimator(this, R.animator.fade_out)
            anim.setTarget(animationUsingXml1Acb);
            anim.start();
        }

        multiAnimationUsingXmlButton = findViewById(R.id.acb_multi_animation_xml)
        multiAnimationUsingXmlButton.setOnClickListener {
            val anim = AnimatorInflater.loadAnimator(this, R.animator.multi_anim)
            anim.setTarget(multiAnimationUsingXmlButton)
            anim.duration = 1000
            anim.startDelay = 10
            anim.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator) {
                    Toast.makeText(this@PropertyAnimationActivity, "Started...", Toast.LENGTH_SHORT)
                        .show()
                }
            })
            anim.start()
        }
    }
}