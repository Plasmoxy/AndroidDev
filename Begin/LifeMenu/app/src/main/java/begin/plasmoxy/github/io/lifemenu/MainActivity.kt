package begin.plasmoxy.github.io.lifemenu

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setListeners()
    }

    fun setListeners() {

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Do you really wanna entern't?", Snackbar.LENGTH_LONG)
                    .setAction("Non't", {
                        finishAndRemoveTask()
                    }).show()
        }

        buttonLaunchSecondary.setOnClickListener {



            val newIntent = Intent(this, SecondaryActivity::class.java).apply {
                putExtra("value", seekBar.progress)
                putExtra("bigText", switchBigText.isChecked)
                putExtra("redText", switchRedText.isChecked)
            }
            startActivity(newIntent)
        }

        seekBar.setOnSeekBarChangeListener(MainSeekBarListener(textOne))

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {

            R.id.action_settings -> {
                toast("settings selected")
                toast("XD")
                true
            }

            R.id.ankoSnackbar -> {
                snackbar(rootLinear.rootView, "Hello Anko by JetBrains !")
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
