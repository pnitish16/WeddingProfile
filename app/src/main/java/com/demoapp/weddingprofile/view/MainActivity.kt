package com.demoapp.weddingprofile.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.demoapp.weddingprofile.R
import com.demoapp.weddingprofile.adapter.ProfileListAdapter
import com.demoapp.weddingprofile.databinding.ActivityMainBinding
import com.demoapp.weddingprofile.repository.MainViewState
import com.demoapp.weddingprofile.viewmodel.ProfileViewModel
import com.demoapp.weddingprofile.viewmodel.ProfileViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var profileListAdapter: ProfileListAdapter
    private lateinit var binding : ActivityMainBinding
    private lateinit var profileViewModel : ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewModelFactory = ProfileViewModelFactory( application)

        profileViewModel = ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)

        binding.lifecycleOwner = this

        binding.viewmodel = profileViewModel
        setupListAdapter()
        initObserver()
//        profileViewModel.getProfiles()
        profileViewModel.getOfflineItems()
    }

    private fun setupListAdapter() {
        val viewModel = binding.viewmodel
        if (viewModel != null) {
            profileListAdapter = ProfileListAdapter(viewModel)
            val layoutManager =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.rvProfileList.adapter = profileListAdapter
            binding.rvProfileList.layoutManager = layoutManager

            val snapHelper: LinearSnapHelper = object : LinearSnapHelper() {
                override fun findTargetSnapPosition(
                    layoutManager: RecyclerView.LayoutManager,
                    velocityX: Int,
                    velocityY: Int
                ): Int {
                    val centerView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
                    val position = layoutManager.getPosition(centerView)
                    var targetPosition = -1
                    if (layoutManager.canScrollHorizontally()) {
                        targetPosition = if (velocityX < 0) {
                            position - 1
                        } else {
                            position + 1
                        }
                    }
                    if (layoutManager.canScrollVertically()) {
                        targetPosition = if (velocityY < 0) {
                            position - 1
                        } else {
                            position + 1
                        }
                    }
                    val firstItem = 0
                    val lastItem = layoutManager.itemCount - 1
                    targetPosition =
                        Math.min(lastItem, Math.max(targetPosition, firstItem))
                    return targetPosition
                }
            }
            snapHelper.attachToRecyclerView(binding.rvProfileList)

        } else {
            Log.d("error", "ViewModel not initialized when attempting to set up adapter.")
        }
    }

    private fun initObserver() {
        profileViewModel.results.observe(this,
            Observer<MainViewState> {
                when (it) {
                    is MainViewState.Loading -> binding.pbProfileList.visibility = View.VISIBLE
                    is MainViewState.Content -> {
                        binding.pbProfileList.visibility = View.GONE
                        profileListAdapter.submitList(it.profiles)
                    }
                    is MainViewState.Error -> {
                        binding.pbProfileList.visibility = View.GONE
                        Toast.makeText(
                            this@MainActivity, it.throwable.localizedMessage,
                            Toast.LENGTH_LONG
                        ).show()
                        Log.e("Weather", "Exception loading data", it.throwable)
                    }
                }
            })

        profileViewModel.profileOfflineList.observe(this, Observer {
            if(it.isEmpty()){
                profileViewModel.getProfiles()
            }else{
                binding.pbProfileList.visibility = View.VISIBLE
                profileListAdapter.notifyDataSetChanged()
                profileListAdapter.submitList(it)
            }
        })

        profileViewModel.toastMessage.observe(this , Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }
}