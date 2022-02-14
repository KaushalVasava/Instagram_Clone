package com.lahsuak.apps.instagramclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.lahsuak.apps.instagramclone.util.UserUtility.getSharedPref
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView.setupWithNavController(navHostFragment.findNavController())

        bottomNavigationView.setOnItemReselectedListener { /* NO-OP */ }
        navController = findNavController(R.id.navHostFragment)

        val inflater = navHostFragment.findNavController().navInflater
        val graph = inflater.inflate(R.navigation.main_nav_graph)
        if (getSharedPref(this))
            graph.setStartDestination(R.id.homeFragment)
        else {
           // val checkUser =
            graph.setStartDestination(R.id.loginFragment)
        }
        navHostFragment.findNavController().graph = graph

        navHostFragment.findNavController()
            .addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.loginFragment, R.id.registerFragment ->
                        bottomNavigationView.visibility = View.GONE
                    else ->
                        bottomNavigationView.visibility = View.VISIBLE
                }
            }
    }
}