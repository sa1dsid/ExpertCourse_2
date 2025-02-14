package com.example.expertcourse_2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.expertcourse_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel: GameViewModel = GameViewModel()

        binding.skipButton.setOnClickListener {
            val uiState: GameUiState = viewModel.skip()
            uiState.update(binding = binding)
        }

        binding.nextButton.setOnClickListener {
            val uiState: GameUiState = viewModel.next()
            uiState.update(binding = binding)
        }


        binding.checkButton.setOnClickListener {
            val uiState: GameUiState = viewModel.check(text = binding.inputEditText.text.toString())
            uiState.update(binding = binding)
        }

        binding.inputEditText.addTextChangedListener {
            val uiState: GameUiState = viewModel.handleUserInput(text = it.toString())
        }

        val uiState: GameUiState = viewModel.init()
        uiState.update(binding = binding)

    }
}

