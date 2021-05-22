package com.example.myapplication.kotlin
import java.io.Serializable

class Person (
    var id: Int? = null,
    var name: String? = null,
    var age: Int? = null,
    var intro: String? = null
) : Serializable