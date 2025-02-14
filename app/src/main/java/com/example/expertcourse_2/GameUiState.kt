package com.example.expertcourse_2

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isVisible
import androidx.transition.Visibility
import com.example.expertcourse_2.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

interface GameUiState {

    fun update(binding: ActivityMainBinding)

    abstract class Abstract(
        private val shuffledValue: String,
        private val inputUiState: InputUiState,
        private val skipVisibility: Int,
        private val checkUiState: CheckUiState,
        private val nextVisibility: Int,
    ) : GameUiState {

        override fun update(binding: ActivityMainBinding) = with(binding) {
            shuffledWordTextView.text = shuffledValue
            inputUiState.update(inputlayout, inputEditText)
            skipButton.visibility = skipVisibility
            checkUiState.update(checkButton)
            nextButton.visibility = nextVisibility
        }
    }

    data class Initial(private val shuffledWord: String) : Abstract(
        shuffledWord,
        InputUiState.Initial,
        skipVisibility = View.VISIBLE,
        checkUiState = CheckUiState.Disable,
        nextVisibility = View.GONE
    )

    data class Insufficient(private val shuffledWord: String) : Abstract(
        shuffledWord,
        InputUiState.EnabledNoError,
        skipVisibility = View.VISIBLE,
        checkUiState = CheckUiState.Disable,
        nextVisibility = View.GONE
    )

    data class Sufficient(private val shuffledWord: String) : Abstract(
        shuffledWord,
        InputUiState.EnabledNoError,
        skipVisibility = View.VISIBLE,
        checkUiState = CheckUiState.Enabled,
        nextVisibility = View.GONE
    )

    data class Correct(private val shuffledWord: String) : Abstract(
        shuffledWord,
        InputUiState.Correct,
        skipVisibility = View.GONE,
        checkUiState = CheckUiState.Invisible,
        nextVisibility = View.VISIBLE
    )

    data class Incorrect(private val shuffledWord: String) : Abstract(
        shuffledWord,
        InputUiState.Incorrect,
        skipVisibility = View.VISIBLE,
        checkUiState = CheckUiState.Disable,
        nextVisibility = View.GONE
    )

}

interface InputUiState {

    fun update(inputLayout: TextInputLayout, inputEditText: TextInputEditText)

    abstract class Abstract(
        private val errorIsVisible: Boolean,
        private val enabled: Boolean,
        private val clearText: Boolean
    ) : InputUiState {

        override fun update(inputLayout: TextInputLayout, inputEditText: TextInputEditText) {
            inputLayout.isErrorEnabled = errorIsVisible
            if (errorIsVisible)
                inputLayout.error = inputLayout.context.getString(R.string.incorrect_message)
            inputLayout.isEnabled = enabled
            if (clearText)
                inputEditText.setText(R.string.empty)
        }
    }

    object Initial : Abstract(false, true, true)
    object EnabledNoError : Abstract(false, true, false)
    object Correct : Abstract(false, false, false)
    object Incorrect : Abstract(true, true, false)
}


interface CheckUiState {

    fun update(checkButton: AppCompatButton)

    abstract class Abstract(
        private val visible: Int,
        private val enabled: Boolean,
    ) : CheckUiState {

        override fun update(checkButton: AppCompatButton) = with(checkButton) {
            visibility = visible
            isEnabled = enabled

        }
    }

    object Disable : Abstract(View.VISIBLE, false)

    object Enabled : Abstract(View.VISIBLE, true)

    object Invisible : Abstract(View.GONE, false)

}












