package com.jonkandidi.bibliotecadejuegos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.jonkandidi.bibliotecadejuegos.databinding.ActivityMainBinding
import com.jonkandidi.bibliotecadejuegos.modelo.JuegoViewModel
import androidx.activity.viewModels
import com.jonkandidi.bibliotecadejuegos.modelo.BBDD
import com.jonkandidi.bibliotecadejuegos.modelo.JuegoViewModelFactory
import com.jonkandidi.bibliotecadejuegos.modelo.Repositorio

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    val miDataBase by lazy { BBDD.getDatabase(this)}
    val miRepositorio by lazy { Repositorio(miDataBase.miDAO()) }
    val miViewModel:JuegoViewModel by viewModels { JuegoViewModelFactory(miRepositorio) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_content_main) as androidx.navigation.fragment.NavHostFragment
        val navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}