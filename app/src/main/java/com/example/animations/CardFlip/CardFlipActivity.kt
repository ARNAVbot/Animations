package com.example.animations.CardFlip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.animations.R

/**
//NOTE: do check out this -> https://www.thedroidsonroids.com/blog/how-to-add-card-flip-animation-to-your-android-app
**/
class CardFlipActivity : FragmentActivity() {

    var showingBack = false
    private lateinit var cardFlipButton: AppCompatButton
    private lateinit var container: FrameLayout
    private val distance = 8000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity_card_flip)
        cardFlipButton = findViewById(R.id.acb_flip)
//        container = findViewById(R.id.container)
//        val scale: Float = getResources().getDisplayMetrics().density
//        container.setCameraDistance(distance * scale)
        cardFlipButton.setOnClickListener { flipCard() }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, CardFrontFragment())
                .commit()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        showingBack = false
    }

    private fun flipCard() {
        if (showingBack) {
            supportFragmentManager.popBackStack()
            return
        }

        // Flip to the back.

        showingBack = true

        // Create and commit a new fragment transaction that adds the fragment for
        // the back of the card, uses custom animations, and is part of the fragment
        // manager's back stack.

        supportFragmentManager.beginTransaction()

            // Replace the default fragment animations with animator resources
            // representing rotations when switching to the back of the card, as
            // well as animator resources representing rotations when flipping
            // back to the front (e.g. when the system Back button is pressed).

                // card_flip_right_in is the enter animation for the second fragment
                // card_flip_right_out is the exit animation for the 1st fragment which is going out of view
            // card_flip_left_in is the enter animation of the fragment from the back stack. this is done
                // when this fragment is poped from the back stack essentailly in the case when back is pressed
            // card_flip_left_out is the exit animation that is being popped out from the back stack.

                // NOTE: we have used the following flags
            //  android:valueFrom="some_value"
            //        android:valueTo="some_other_value"
            //imagine that you just landed on screen and now u start the animation .
            // then the current fragment will rotate around the y-axis and will rotate in the clocwise direction 180 degrees.
            // this can be done by setting valueFrom = "0" and valueTo = "-180". ( -ve because it is clockwise)
            // Similary, the incoming fragment is just behind this fragment . So when this incoming fragment is to brought in
            // it also will rotate around the y-axis , also in a clockwise fashion from "-180" degrees (- beacuse it is behind this fragment and also because it is clockwise) to "0" degrees.
            // so in this case     valueFrom = "-180" and valueTo = "0".
            // Also, when back is pressed , all this transfromation has to go in opposite direction.
            //TIP: try changing these values from -ve to +ve. U'll know the difference
            .setCustomAnimations(
                R.animator.card_flip_right_in,
                R.animator.card_flip_right_out,
                R.animator.card_flip_left_in,
                R.animator.card_flip_left_out
            )

            // Replace any fragments currently in the container view with a
            // fragment representing the next page (indicated by the
            // just-incremented currentPage variable).
            .replace(R.id.container, CardBackFragment())

            // Add this transaction to the back stack, allowing users to press
            // Back to get to the front of the card.
            .addToBackStack(null)

            // Commit the transaction.
            .commit()
    }

    /**
     * A fragment representing the front of the card.
     */
    class CardFrontFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ) : View = inflater.inflate(R.layout.fragment_card_front, container, false)

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            var container:LinearLayout? = view.findViewById(R.id.container)
            if(container != null) {
                // The following increase the distance of rotation from the screen and hence the rotation looks smooth
                val scale: Float = getResources().getDisplayMetrics().density
                container.setCameraDistance(8000 * scale)
            }
        }
    }

    /**
     * A fragment representing the back of the card.
     */
    class CardBackFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View = inflater.inflate(R.layout.fragment_card_back, container, false)

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            var container:LinearLayout? = view.findViewById(R.id.container)
            if(container != null) {
                // The following increase the distance of rotation from the screen and hence the rotation looks smooth
                val scale: Float = getResources().getDisplayMetrics().density
                container.setCameraDistance(8000 * scale)
            }
        }
    }
}