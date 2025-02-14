package com.example.expertcourse_2

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before


class GameViewModelTest {

    private lateinit var viewModel: GameViewModel

    @Before
    fun setup() {
        viewModel = GameViewModel(repository = FakeRepository())
    }


    @Test
    fun testcase1() {
        var actual: GameUiState = viewModel.init()
        var expected: GameUiState = GameUiState.Initial(shuffedWord = "said")
        assertEquals(expected, actual)

        var actual = viewModel.handleUserInput(text = "said")
        var expected = GameUiState.SufficientInput(shuffedWord = "said")
        assertEquals(expected, actual)

        var actual = viewModel.check(text = "said")
        var expected = GameUiState.Correct(shuffedWord = "said2")
        assertEquals(expected, actual)

    }

    @Test
    fun testcase2() {
        var actual: GameUiState = viewModel.init()
        var expected: GameUiState = GameUiState.Initial(shuffedWord = "said")
        assertEquals(expected, actual)

        var actual = viewModel.handleUserInput(text = "saad")
        var expected = GameUiState.SufficientInput(shuffedWord = "said")
        assertEquals(expected, actual)

        var actual = viewModel.check(text = "saad")
        var expected = GameUiState.Incorrect(shuffedWord = "said")
        assertEquals(expected, actual)

        var actual = viewModel.handleUserInput(text = "said")
        var expected = GameUiState.SufficientInput(shuffedWord = "said")
        assertEquals(expected, actual)

        var actual = viewModel.check(text = "said")
        var expected = GameUiState.Correct(shuffedWord = "said2")
        assertEquals(expected, actual)
    }

    @Test
    fun testcase3() {
        var actual: GameUiState = viewModel.init()
        var expected: GameUiState = GameUiState.Initial(shuffedWord = "said")
        assertEquals(expected, actual)

        var actual = viewModel.handleUserInput(text = "saad")
        var expected = GameUiState.SufficientInput(shuffedWord = "said")
        assertEquals(expected, actual)

        var actual = viewModel.check(text = "saad")
        var expected = GameUiState.Incorrect(shuffedWord = "said")
        assertEquals(expected, actual)

        var actual = viewModel.skip()
        var expected = GameUiState.Initial(shuffedWord = "said2")
        assertEquals(expected, actual)
    }

}

private class FakeRepository : GameRepository {

    private val originalList: List<String> = listOf("said", "said2")

    private val shuffledList = originalList.map { it.reversed() }

    private var index = 0

    override fun shuffedWord(): String = shuffledList[index]

    override fun originalWord(): String = originalList[index]

    /**
    override fun check(text: String): CorrectAndUserInputText {
    return CorrectAndUserInputText(
    shuffedWord = shuffedWord(),
    userInputText = userInputText
    )
    }
     */
    override fun next() {
        index++
        if (index == originalList.size) {
            index = 0
        }
    }

    override fun skip() {
        index++
        if (index == originalList.size) {
            index = 0
        }
    }


}