package com.example.sunwayexam.ui

import android.app.Dialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.view.forEach
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.sunwayexam.R
import com.example.sunwayexam.databinding.ActivityMainBinding
import com.example.sunwayexam.model.Language
import com.example.sunwayexam.ui.dialog.LanguageDialogFragment
import com.example.sunwayexam.ui.dialog.SelectCropContent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { _, destination, bundle ->
            if (destination.id == R.id.nav_home) {
                binding.appBarMain.toolbar.menu.forEach {
                    it.isVisible = true
                }
            } else {
                binding.appBarMain.toolbar.menu.forEach {
                    it.isVisible = false
                }
            }
            // 設定標題文字
            val title = bundle?.getString("title") ?: destination.label.toString()
            setToolbarTitle(title)
        }
        observe()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.change_language -> {
                viewModel.showDialogMessage()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.uiState
                        .map { it.showDialog }
                        .distinctUntilChanged()
                        .collect { show ->
                            if (show) {
                                showComposeDialog()
                                viewModel.dialogShown()
                            }
                        }
                }
            }
        }
    }

    /**
     * set Toolbar title
     */
    private fun setToolbarTitle(title: String) {
        binding.appBarMain.toolbar.title = title
    }

    /**
     * show change language dialog
     */
    private fun showComposeDialog() {
        if (!this.isDestroyed) {
            LanguageDialogFragment(
                language = viewModel.uiState.value.language,
                changeLanguage = {
                    viewModel.setLanguageCode(it)
                }
            ).show(supportFragmentManager, LanguageDialogFragment.TAG)
        }
    }
}