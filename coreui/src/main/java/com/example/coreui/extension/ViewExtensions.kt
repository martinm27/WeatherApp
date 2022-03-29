package com.example.coreui.extension

import android.animation.Animator
import android.animation.ValueAnimator
import android.view.View

private const val HEIGHT_INVISIBLE = 0
private const val VIEW_HEIGHT_CHANGE_ANIMATION_DURATION = 300L
const val ALPHA_OPAQUE = 1f
const val ALPHA_TRANSPARENT = 0f
const val DEFAULT_QUICK_ANIMATION_DURATION = 200L

/**
 * View's alpha property will be animated to [ALPHA_OPAQUE] based on [duration]
 */
fun View.fadeIn(duration: Long = DEFAULT_QUICK_ANIMATION_DURATION) {
    animate().alpha(ALPHA_OPAQUE).duration = duration
}

/**
 * View's alpha property will be animated to [ALPHA_TRANSPARENT] based on [duration]
 */
fun View.fadeOut(duration: Long = DEFAULT_QUICK_ANIMATION_DURATION) {
    animate().alpha(ALPHA_TRANSPARENT).duration = duration
}

fun View.animateViewHeight(
    from: Int,
    to: Int,
    duration: Long = VIEW_HEIGHT_CHANGE_ANIMATION_DURATION,
    onEnd: (() -> Unit)? = null
) {
    ValueAnimator.ofInt(from, to).run {
        addUpdateListener {
            layoutParams = layoutParams.apply {
                height = it.animatedValue as Int
            }
        }

        onEnd?.let {
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) = it()
            })
        }

        this.duration = duration
        start()
    }
}

fun View.expandViewHeight() {
    (parent as View).let {
        val widthSpec = View.MeasureSpec.makeMeasureSpec(it.width, View.MeasureSpec.EXACTLY)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(it.height, View.MeasureSpec.UNSPECIFIED)
        measure(widthSpec, heightSpec)
    }
    animateViewHeight(HEIGHT_INVISIBLE, measuredHeight)
}

fun View.collapseViewHeight() {
    animateViewHeight(height, HEIGHT_INVISIBLE)
}

open class AnimatorListenerAdapter : Animator.AnimatorListener {
    override fun onAnimationRepeat(animation: Animator?) {}
    override fun onAnimationEnd(animation: Animator?) {}
    override fun onAnimationCancel(animation: Animator?) {}
    override fun onAnimationStart(animation: Animator?) {}
}
