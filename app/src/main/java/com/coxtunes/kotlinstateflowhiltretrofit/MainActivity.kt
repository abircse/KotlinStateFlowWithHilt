package com.coxtunes.ponnobeponi.kotlinstateflowhiltretrofit

import android.net.Uri
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.coxtunes.ponnobeponi.kotlinstateflowhiltretrofit.adapter.CategoryAdapter
import com.coxtunes.ponnobeponi.kotlinstateflowhiltretrofit.databinding.ActivityMainBinding
import com.coxtunes.ponnobeponi.kotlinstateflowhiltretrofit.viewmodel.CategoryViewModel
import com.coxtunes.kotlinstateflowhiltretrofit.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewmodel: CategoryViewModel by viewModels()

    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun onCreated(instance: Bundle?) {
        setupViews()
        setupViewsActions()
    }

    private fun setupViews() {
        viewmodel.loadCategory()

        binding.swipelayout.setOnRefreshListener {
            viewmodel.loadCategory()
        }
    }

    private fun setupViewsActions() {
        lifecycleScope.launchWhenStarted {
            viewmodel.loadCategoryEvent.collect { event ->
                when (event) {
                    is CategoryViewModel.ResponseEvent.Loading -> {
                        binding.swipelayout.isRefreshing = true
                    }

                    is CategoryViewModel.ResponseEvent.Success -> {
                        binding.swipelayout.isRefreshing = false
                        event.data.let {
                            binding.recyclerview.apply {
                                setHasFixedSize(true)
                                layoutManager = LinearLayoutManager(this@MainActivity)
                                val categoryAdapter =
                                    CategoryAdapter(this@MainActivity, event.data.data)
                                adapter = categoryAdapter
                            }
                        }
                    }

                    is CategoryViewModel.ResponseEvent.Failure -> {
                        binding.swipelayout.isRefreshing = false
                    }
                    else -> Unit
                }

            }
        }
    }

    override fun processIntentData(data: Uri) {}
}