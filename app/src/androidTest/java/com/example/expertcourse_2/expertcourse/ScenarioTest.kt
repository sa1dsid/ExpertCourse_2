package com.example.expertcourse

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.expertcourse.game.GamePage
import com.example.expertcourse_2.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Before
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class ScenarioTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var gamePage: GamePage

    @Before
    fun setup(){
        gamePage = GamePage(word = "animal".reversed())
    }


    // Короче у тебя тесты говна, переделывай!


    /**
     * UG-01
     */

    @Test
    fun testcase1() {

        gamePage.assertInitialState()

        gamePage.addInput(text = "animal")
        gamePage.assertSufficientInputState()

        gamePage.clickCheck()
        gamePage.assertCorrectState()

        gamePage.clickNext()

        gamePage = GamePage(word = "auto".reversed())
        gamePage.assertInitialState()
    }

    /**
     * UG-02
     */

    @Test
    fun testcase2() {

        gamePage.assertInitialState()

        gamePage.addInput(text = "anima")
        gamePage.assertInsufficientInputState()

        gamePage.addInput(text = "l")
        gamePage.assertSufficientInputState()

        gamePage.clickCheck()
        gamePage.assertCorrectState()

        gamePage.clickNext()

        gamePage = GamePage(word = "auto".reversed())
        gamePage.assertInitialState()

    }

    /**
     * UG-03
     */

    @Test
    fun testcase3() {

        gamePage.assertInitialState()

        gamePage.addInput(text = "animal")
        gamePage.assertSufficientInputState()

        gamePage.clickCheck()
        gamePage.assertCorrectState()

        gamePage.clickSkip()

        gamePage = GamePage(word = "auto".reversed())
        gamePage.assertInitialState()
    }
}