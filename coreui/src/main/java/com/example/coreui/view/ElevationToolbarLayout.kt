package com.example.coreui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import com.example.coreui.R

private const val TOOLBAR_INDEX = 0
private const val CHILD_VIEW_INDEX = 1

class ElevationToolbarLayout : LinearLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        clipToPadding = false
        orientation = VERTICAL
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (isInEditMode) {
            return
        }

        val toolbar = getChildAt(TOOLBAR_INDEX)
            ?: throw IllegalStateException("First child (toolbar) missing")
        val elevationPx = resources.getDimension(R.dimen.elevation_toolbar_elevation)

        (getChildAt(CHILD_VIEW_INDEX))?.let {
            when (it) {
                is WithScrollState -> it.setListener { scrollY ->
                    scrollChanged(
                        toolbar,
                        scrollY,
                        elevationPx
                    )
                }
                else -> throw IllegalStateException("Second child has unsupported view type")
            }

        } ?: throw IllegalStateException("Missing second (scrollable) child")
    }

    private fun scrollChanged(toolbar: View, scrollY: Int, maxElevation: Float) {
        toolbar.elevation = Math.min(scrollY.toFloat(), maxElevation)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

open class ScrollViewWithScrollState : ScrollView, WithScrollState {

    private var listener: ((Int) -> Unit)? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun setListener(listener: (Int) -> Unit) {
        this.listener = listener
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        listener?.invoke(t)
    }

}

interface WithScrollState {
    fun setListener(listener: (Int) -> Unit)
}
