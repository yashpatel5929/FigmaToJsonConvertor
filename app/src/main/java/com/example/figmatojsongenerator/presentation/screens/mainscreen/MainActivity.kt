package com.example.figmatojsongenerator.presentation.screens.mainscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.figmatojsongenerator.databinding.ActivityMainBinding
import com.example.figmatojsongenerator.presentation.screens.optionscreen.OptionsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
   lateinit var binding : ActivityMainBinding
   val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()
    }

    private fun setData() {
        binding.btnSubmit.setOnClickListener {
            if(binding.editTextFigmaAuthToken.text?.isNotEmpty() == true && binding.editTextFigmaLink.text?.isNotEmpty() == true &&
                binding.editTextFilePath.text?.isNotEmpty() == true
            ) {
                viewModel.saveFigmaAuthToke(binding.editTextFigmaAuthToken.text?.trim().toString())
                Intent(this@MainActivity, OptionsActivity::class.java).apply {
                    putExtra("filePath" , binding.editTextFilePath.text?.trim().toString())
                    startActivity(this)
                }
            }else {
                Toast.makeText(this , "Please Provide all Fields" , Toast.LENGTH_LONG).show()
            }
        }
    }


}