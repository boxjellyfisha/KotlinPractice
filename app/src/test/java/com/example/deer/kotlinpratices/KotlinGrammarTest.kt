package com.example.deer.kotlinpratices

import org.junit.Test

class KotlinGrammarTest {
    @Test
    fun infixTest() {
        ("Leo" sayHelloTo "Kelly").also(::print)
    }
    private infix fun String.sayHelloTo(name: String): String = "Hi, $name. There is $this."


    data class Person(private val name: String, private val work: String) {
        constructor(input: Pair<String, String>): this(input.first, input.second)
        fun printIt() = println("$name is a $work!")
    }

    @Test
    fun toTest() {
        /* With data class */
        val person1 = Person("Alice" to "Engineer")
        person1.printIt()

        /* Use Pair<any, any> */
        val person2 = "Alice" to "Engineer"
        person2.also(::println)

        /* Destructuring declaration */
        val (name, work) = "Alice" to "Engineer"
        print("$name become a $work!")
    }

    /*  */
}