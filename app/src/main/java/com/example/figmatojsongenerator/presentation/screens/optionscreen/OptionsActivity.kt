package com.example.figmatojsongenerator.presentation.screens.optionscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.figmatojsongenerator.R
import com.example.figmatojsongenerator.databinding.ActivityOptionsBinding
import com.example.figmatojsongenerator.presentation.utils.ScreenType
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class OptionsActivity : AppCompatActivity() {
    lateinit var binding : ActivityOptionsBinding
    val viewModel by viewModels<OptionScreenViewModel>()
    lateinit var filePath : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        filePath = intent.getStringExtra("filePath") ?: ""
        setData()
    }

    private fun setData() {
        lifecycleScope.launch {

            viewModel.isLoading.collect {
                if(it) {
                    binding.progressLoading.visibility = View.VISIBLE
                    binding.getLoginJson.visibility = View.GONE
                    binding.getSignUpData.visibility = View.GONE
                    binding.jsonText.visibility = View.GONE
                }
                else {
                    binding.progressLoading.visibility = View.GONE
                    binding.getLoginJson.visibility = View.VISIBLE
                    binding.getSignUpData.visibility = View.VISIBLE
                    binding.jsonText.visibility = View.VISIBLE
                }
            }

        }
        lifecycleScope.launch {
            viewModel.jsonValue.collect {
                binding.jsonText.text = GsonBuilder().setPrettyPrinting().create().toJson(it).toString()
            }
        }
        viewModel.getFigmaData(filePath)
        binding.jsonText.movementMethod = ScrollingMovementMethod()
        binding.getLoginJson.setOnClickListener {
            viewModel.getJson(ScreenType.LOGIN.screenName)
        }

        binding.getSignUpData.setOnClickListener {
            viewModel.getJson(ScreenType.SIGNUP.screenName)
        }
    }

}