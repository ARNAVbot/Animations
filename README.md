# Animations

## Animated Vector Drawables
Starting from android 25 AnimatedVectorDrawable run on the render thread, this means even if UI thread is doing the heavy lifting animation may be smooth.
AnimatedVectorDrawableCompat is defined in XML same as AnimatedVectorDrawable.
Basically, we need to define 3 main things
1.Vector file itself
2. Animation file
3. Relation file for vector and animation
Now, these three can be either in one XML of 3 different XML.
Not covered in this tutorial. To read more about it , go here -> https://medium.com/techshots/vector-drawable-animation-e553790c8be4.
We could programmatically write the Animated Vector Drawable, but that will be tedious. Hence here, we’ll be using https://shapeshifter.design/ to help create one.

All the different properties that can be animated using Property animation are found here -> https://developer.android.com/guide/topics/graphics/prop-animation.html#views

## References
1. https://medium.com/mobile-app-development-publication/create-your-own-animated-vector-drawable-on-android-app-3f8fa9bb08c3
2. https://medium.com/techshots/vector-drawable-animation-e553790c8be4
3. https://guides.codepath.com/android/animations
4. https://developer.android.com/guide/topics/graphics/drawable-animation
5. https://developer.android.com/guide/topics/graphics/prop-animation.html#views

## Future scope
1. Animate layout changes to ViewGroup objects -> Refer https://developer.android.com/guide/topics/graphics/prop-animation.html#views and https://guides.codepath.com/android/animations#layout-animations
2. Animate view state changes using StateListAnimator -> Refer https://developer.android.com/guide/topics/graphics/prop-animation.html#views
3. Activity transition -> Refer https://guides.codepath.com/android/animations#activity-transitions
4. Fragment transitions -> Refer https://guides.codepath.com/android/animations#fragment-transitions
