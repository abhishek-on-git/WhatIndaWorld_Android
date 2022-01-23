package com.wcp.whatindaworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.wcp.whatindaworld.databinding.ActivityHomeBinding
import com.wcp.whatindaworld.presentation.adapter.HeadlinesAdapter
import com.wcp.whatindaworld.presentation.viewmodel.NewsViewModel
import com.wcp.whatindaworld.presentation.viewmodel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var mHeadlinesAdapter: HeadlinesAdapter
    @Inject
    lateinit var mViewModelFactory: NewsViewModelFactory
    lateinit var mViewModel: NewsViewModel
    private lateinit var mBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mViewModel = ViewModelProvider(this, mViewModelFactory)
            .get(NewsViewModel::class.java)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        mBinding.bottomNavView.setupWithNavController(navController)
    }
}