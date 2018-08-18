package com.shardbytes.plasmoxy.juncc.manualnavigationdrawer

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false) // disable app title
        
        // method below
        nav_view.setNavigationItemSelectedListener(this)
        
        // setup hamburger
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        
        // set fragment if activity is freshly created, if its something like device rotate or stuff, we keep the selected item and fragment !
        if (savedInstanceState == null) {
            switchFragment(AccountFragment())
            nav_view.setCheckedItem(R.id.nav_account)
        }
    }

    fun switchFragment(f: Fragment) =
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, f)
                    .commit()

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            
            R.id.nav_account -> switchFragment(AccountFragment())
            R.id.nav_info -> switchFragment(InfoFragment())
            R.id.nav_photo -> switchFragment(PhotoFragment())
            
            R.id.nav_message -> toast("Hello !")
            R.id.nav_faggot -> toast("YOR A FAGGOT")
            
        }
        
        drawer_layout.closeDrawer(GravityCompat.START)
        
        return true
    }
    

    // close navigation drawer with back button
    override fun onBackPressed() {
        
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed() // closes activity and stuff
        }
    }
    
}
