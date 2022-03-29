package com.example.coreui.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class PulleyScrollView : ScrollViewWithScrollState {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        isNestedScrollingEnabled = true
        overScrollMode = View.OVER_SCROLL_NEVER
        isVerticalScrollBarEnabled = false
    }

    private var lastY = 0f

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_MOVE && lastY < ev.y && scrollY == 0) {
            lastY = ev.y
            return true
        }
        lastY = ev.y
        return super.onTouchEvent(ev)
    }

    override fun dispatchNestedPreScroll(dx: Int, dy: Int, consumed: IntArray?, offsetInWindow: IntArray?): Boolean =
            if (scrollY == 0 && dy < 0) true else super.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow)
}
