package com.example.expertcourse_2

import org.w3c.dom.Text

class GameViewModel(
    val repository: GameRepository
) {
    fun next(): GameUiState{
        repository.next()
        return init()
    }


    fun check(text: String): GameUiState{
        val shuffleWord = repository.shuffledWord()
        val originWord = repository.originalWord()
        return if (originWord.equals(text, ignoreCase = true))
            GameUiState.Correct(shuffleWord)
        else
            GameUiState.Incorrect(shuffleWord)

    }

    fun skip(): GameUiState{
        repository.next()
        return init()
    }

    fun handleUserInput(text: String): GameUiState{
        val shuffledWord = repository.shuffledWord()
        return if (text.length == shuffledWord.length) GameUiState.Sufficient(shuffledWord)
        else GameUiState.Insufficient(shuffledWord)
    }

    fun init(): GameUiState{
        val shuffledWord = repository.shuffledWord()
        return GameUiState.Initial(shuffledWord)
    }
}