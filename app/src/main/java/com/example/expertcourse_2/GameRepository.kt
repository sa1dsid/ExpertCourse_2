package com.example.expertcourse_2

interface GameRepository {

    fun shuffledWord(): String

    fun originalWord(): String

    fun next()

    fun skip()

}
