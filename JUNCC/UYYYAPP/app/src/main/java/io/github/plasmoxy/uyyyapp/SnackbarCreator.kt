package io.github.plasmoxy.uyyyapp

import android.support.design.widget.Snackbar
import android.view.View

class SnackbarCreator(var v : View) {
    fun create(a: CharSequence) {
        Snackbar.make(v, a, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    }
}