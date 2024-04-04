package com.example.eskooly

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.example.eskooly.databinding.ActivityHomeScreenBinding
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)

        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarHomeScreen.toolbar)
        binding.appBarHomeScreen.toolbar

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        val customView = layoutInflater.inflate(R.layout.custom_app_bar,null)
        supportActionBar?.customView = customView

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_home_screen)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.paidFeeReceiptFragment,
                R.id.admissionLetterFragment,
                R.id.updateLoginDetailsFragment,
                R.id.myTimeTableFragment,
                R.id.testResultsFragment,
                R.id.examResultFragment,
                R.id.homeAssignmentFragment,
                R.id.liveClassFragment,
                R.id.messagingFragment,
                R.id.reportCardFragment,
            ), drawerLayout
        )
        val listener = object:NavController.OnDestinationChangedListener{
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                when(destination.id){
                    R.id.liveClassFragment->{
                        Toast.makeText(this@HomeActivity,"Coming Soonciuwke9n3w8tuw",Toast.LENGTH_LONG).show()

                    }
                    R.id.action_online_store->{
                        Toast.makeText(this@HomeActivity,"Coming Soon",Toast.LENGTH_SHORT).show()

                    }
                }
            }


        }
        navController.addOnDestinationChangedListener(listener)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }




    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home_screen)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}