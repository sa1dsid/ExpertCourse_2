package com.example.expertcourse_2

import com.example.expertcourse_2.databinding.ActivityMainBinding

interface GameUiState {

    fun update(binding: ActivityMainBinding): Unit = throw IllegalStateException("handle this")


    data class Initial(val shuffledWord: String): GameUiState {

    }

    data class Insufficient(val shuffledWord: String): GameUiState {

    }

    data class Sufficient(val shuffledWord: String): GameUiState {

    }

    data class Correct(val shuffledWord: String): GameUiState {

    }

    data class Incorrect(val shuffledWord: String): GameUiState {

    }

}