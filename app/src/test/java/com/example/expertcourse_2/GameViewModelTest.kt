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
        assertInitial(correctWord = "said", actual)

        actual = viewModel.handleUserInput(text = "sa")
        assertInsufficient(correctWord = "said", actual)

        actual = viewModel.handleUserInput(text = "said")
        assertSufficient(correctWord = "said", actual)

        actual = viewModel.check(text = "said")
        assertCorrect(correctWord = "said", actual)

        actual = viewModel.next()
        assertInitial(correctWord = "said2", actual)

    }

    @Test
    fun testcase2() {
        var actual: GameUiState = viewModel.init()
        assertInitial(correctWord = "said", actual)

        actual = viewModel.handleUserInput(text = "saad")
        assertSufficient(correctWord = "said", actual)

        actual = viewModel.check(text = "saad")
        assertIncorrect(correctWord = "said", actual)

        actual = viewModel.handleUserInput(text = "saaddd")
        assertInsufficient(correctWord = "said", actual)

        actual = viewModel.handleUserInput(text = "said")
        assertSufficient(correctWord = "said", actual)

        actual = viewModel.check(text = "said")
        assertCorrect(correctWord = "said", actual)

        actual = viewModel.next()
        assertInitial(correctWord = "said2", actual)

        actual = viewModel.skip()
        assertInitial(correctWord = "said3", actual)
    }

    @Test
    fun testcase3() {
        var actual: GameUiState = viewModel.init()
        assertInitial(correctWord = "said", actual)

        actual = viewModel.handleUserInput(text = "saad")
        assertSufficient(correctWord = "said", actual)

        actual = viewModel.check(text = "saad")
        assertIncorrect(correctWord = "said", actual)

        actual = viewModel.handleUserInput(text = "")
        assertInsufficient(correctWord = "said", actual)

        actual = viewModel.handleUserInput(text = "SoiD")
        assertSufficient(correctWord = "said", actual)

        actual = viewModel.check(text = "SoiD")
        assertIncorrect(correctWord = "said", actual)

        actual = viewModel.handleUserInput(text = "said")
        assertSufficient(correctWord = "said", actual)

        actual = viewModel.check(text = "said")
        assertCorrect(correctWord = "said", actual)

        actual = viewModel.skip()
        assertInitial(correctWord = "said2", actual)

        actual = viewModel.handleUserInput(text = "saadd")
        assertSufficient(correctWord = "said2", actual)

        actual = viewModel.check(text = "saadd")
        assertIncorrect(correctWord = "said2", actual)

        actual = viewModel.handleUserInput(text = "said2")
        assertSufficient(correctWord = "said2", actual)

        actual = viewModel.check(text = "said2")
        assertCorrect(correctWord = "said2", actual)

        actual = viewModel.next()
        assertInitial(correctWord = "said3", actual)
    }

    fun assertInitial(correctWord: String, actual: GameUiState) {
        var expected: GameUiState = GameUiState.Initial(shuffledWord = correctWord)
        assertEquals(expected, actual)
    }

    fun assertSufficient(correctWord: String, actual: GameUiState) {
        var expected: GameUiState = GameUiState.Sufficient(shuffledWord = correctWord)
        assertEquals(expected, actual)
    }

    fun assertIncorrect(correctWord: String, actual: GameUiState) {
        var expected: GameUiState = GameUiState.Incorrect(shuffledWord = correctWord)
        assertEquals(expected, actual)
    }

    fun assertCorrect(correctWord: String, actual: GameUiState) {
        var expected: GameUiState = GameUiState.Correct(shuffledWord = correctWord)
        assertEquals(expected, actual)
    }

    fun assertInsufficient(correctWord: String, actual: GameUiState) {
        var expected: GameUiState = GameUiState.Insufficient(shuffledWord = correctWord)
        assertEquals(expected, actual)
    }

}

private class FakeRepository : GameRepository {

    private val originalList: List<String> = listOf("said", "said2", "said3")

    private val shuffledList = originalList.map { it }

    private var index = 0

    override fun shuffledWord(): String = shuffledList[index]

    override fun originalWord(): String = originalList[index]

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