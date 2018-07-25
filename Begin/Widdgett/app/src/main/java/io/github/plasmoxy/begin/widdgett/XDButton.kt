package io.github.plasmoxy.begin.widdgett

import android.content.Context
import android.view.ViewGroup.LayoutParams
import android.widget.Button
import android.widget.LinearLayout

class XDButton(private val ctx: Context,
               private val superLayout: LinearLayout,
               private val value: Int) : Button(ctx) {
    
    init {
        text = value.toString()
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        //background = ContextCompat.getDrawable(ctx, R.drawable.xdbutton)
        setOnClickListener {multiply()}
    }
    
    fun multiply() {
        val idx = superLayout.indexOfChild(this)
        superLayout.addView(XDButton(ctx, superLayout, value + 1), idx + 1)
        superLayout.addView(XDButton(ctx, superLayout, value + 1), idx)
    }
    
}