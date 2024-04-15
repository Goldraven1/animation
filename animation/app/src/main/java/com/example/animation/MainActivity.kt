package com.example.animation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.view.ViewGroup

class MainActivity : AppCompatActivity() {
    private var textViewBimBim: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        val imageView = findViewById<ImageView>(R.id.imageView)

        val scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 1f, 1f, 2f, 2f, 0.9f, 1f)
        val scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 1f, 1f, 4f, 1.1f, 0.9f, 1f)

        scaleX.duration = 2000
        scaleY.duration = 2000

        val animatorSet = AnimatorSet()
        animatorSet.play(scaleX).with(scaleY)

        imageView.setOnClickListener {
            animatorSet.start()

            textViewBimBim?.let {
                (it.parent as ViewGroup).removeView(it)
            }

            textViewBimBim = TextView(this).apply {
                text = "бимбим бам бам"
                x = imageView.x
                y = imageView.y
                (imageView.parent as ViewGroup).addView(this)
            }
        }

        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)
        val button10 = findViewById<Button>(R.id.button10)

        val buttons = listOf<Button>(button6, button7, button8, button9, button10)

        buttons.forEachIndexed { index, button ->
            val animatorSetButton = AnimatorSet()
            val animation = when (index) {
                0 -> createAlphaAnimation(button)
                1 -> createScaleXAnimation(button)
                2 -> createTranslateAnimation(button)
                3 -> createRotateAnimation(button)
                4 -> createScaleYAnimation(button)
                else -> throw IllegalArgumentException("Invalid index")
            }
            animatorSetButton.play(animation)

            button.setOnClickListener {
                animatorSetButton.start()


                val location = IntArray(2)
                button.getLocationOnScreen(location)

                val buttonCenterX = location[0] + button.width / 2
                val buttonCenterY = location[1] + button.height / 2

                val imageViewCenterX = imageView.width / 2
                val imageViewCenterY = imageView.height / 2

                val moveX = ObjectAnimator.ofFloat(imageView, "translationX", (buttonCenterX - imageViewCenterX).toFloat())
                val moveY = ObjectAnimator.ofFloat(imageView, "translationY", (buttonCenterY - imageViewCenterY).toFloat())

                moveX.duration = 1000
                moveY.duration = 1000

                moveX.start()
                moveY.start()
            }
        }
    }

    private fun createAlphaAnimation(button: Button): ObjectAnimator {
        val animation = ObjectAnimator.ofFloat(button, "alpha", 0f, 1f)
        animation.duration = 1000
        animation.repeatCount = ObjectAnimator.INFINITE
        animation.repeatMode = ObjectAnimator.REVERSE
        return animation
    }

    private fun createScaleXAnimation(button: Button): ObjectAnimator {
        val animation = ObjectAnimator.ofFloat(button, "scaleX", 1f, 1.25f, 0.75f, 1.15f, 0.85f, 1f)
        animation.duration = 500
        animation.repeatCount = ObjectAnimator.INFINITE
        animation.repeatMode = ObjectAnimator.REVERSE
        return animation
    }

    private fun createScaleYAnimation(button: Button): ObjectAnimator {
        val animation = ObjectAnimator.ofFloat(button, "scaleY", 1f, 1.25f, 0.75f, 1.15f, 0.85f, 1f)
        animation.duration = 500
        animation.repeatCount = ObjectAnimator.INFINITE
        animation.repeatMode = ObjectAnimator.REVERSE
        return animation
    }

    private fun createTranslateAnimation(button: Button): ObjectAnimator {
        val animation = ObjectAnimator.ofFloat(button, "translationX", 0f, 200f, 0f)
        animation.duration = 1000
        animation.repeatCount = ObjectAnimator.INFINITE
        animation.repeatMode = ObjectAnimator.REVERSE
        return animation
    }

    private fun createRotateAnimation(button: Button): ObjectAnimator {
        val animation = ObjectAnimator.ofFloat(button, "rotation", 0f, 360f)
        animation.duration = 1000
        animation.repeatCount = ObjectAnimator.INFINITE
        animation.repeatMode = ObjectAnimator.REVERSE
        return animation
    }
}