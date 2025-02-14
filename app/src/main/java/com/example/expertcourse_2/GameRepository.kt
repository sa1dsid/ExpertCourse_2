package com.example.expertcourse_2

interface GameRepository {

    fun shuffledWord(): String

    fun originalWord(): String

    fun next()

    fun skip()

    class Base(
        private val shuffleStrategy: ShuffleStrategy = ShuffleStrategy.Reverse(),
        private val originalList: List<String> = listOf(
            "animal",
            "auto",
            "anecdote",
            "alphabet",
            "elephant",
            "flower",
            "guitar",
            "house",
            "ice",
            "jungle",
            "kangaroo",
            "lemon",
            "mountain",
            "notebook",
            "ocean",
            "pencil",
            "quartz",
            "rainbow",
            "sun",
            "tree",
            "umbrella",
            "volcano",
            "watermelon",
            "xylophone",
            "yellow",
            "zebra"
        )
    ) : GameRepository {

        private val shuffledList = originalList.map { shuffleStrategy.shuffle(it) }

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
}
interface ShuffleStrategy {
    fun shuffle(source: String): String

    class Base: ShuffleStrategy{
        override fun shuffle(source: String): String {
            return source
        }
    }

    class Reverse: ShuffleStrategy{
        override fun shuffle(source: String): String {
            return source.reversed()
        }

    }
}