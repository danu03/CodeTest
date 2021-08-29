package com.danusuhendra.codingtestperintis.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.danusuhendra.codingtestperintis.R
import com.danusuhendra.codingtestperintis.ui.home.list.ListArtisanFragment
import com.danusuhendra.codingtestperintis.ui.home.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        navigateFragment(ListArtisanFragment())
        title = "Home"
        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_home -> {
                    navigateFragment(ListArtisanFragment())
                    title = "Home"
                }
                R.id.nav_profile -> {
                    navigateFragment(ProfileFragment())
                    title = "Profile"
                }
            }
            true
        }

    }

    private fun navigateFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragment, fragment)
                commit()
            }
    }
}