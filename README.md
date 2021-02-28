# Animations

## Animated Vector Drawables
Starting from android 25 AnimatedVectorDrawable run on the render thread, this means even if UI thread is doing the heavy lifting animation may be smooth.
AnimatedVectorDrawableCompat is defined in XML same as AnimatedVectorDrawable.
Basically, we need to define 3 main things
1.Vector file itself
2. Animation file
3. Relation file for vector and animation
Now, these three can be either in one XML of 3 different XML.
Not covered in this tutorial. To read more about it , go here -> https://medium.com/techshots/vector-drawable-animation-e553790c8be4
