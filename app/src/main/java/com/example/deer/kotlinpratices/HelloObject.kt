package com.example.deer.kotlinpratices

data class HelloObject(var message: String = "Hello world.",
                       var detail: String = "I'm practicing in Kotlin.")


class Second (k:String){
    init {

    }
    constructor(i: Int) : this("hello")
}
