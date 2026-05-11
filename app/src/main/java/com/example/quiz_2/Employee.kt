package com.example.quiz_2

// This is a plain data class — Firebase uses it to save/load from the cloud.
// Firebase needs a no-argument constructor, which Kotlin provides automatically
// when all fields have default values (= null).
data class Employee(
    val id: String? = null,
    val name: String? = null,
    val jobTitle: String? = null,
    val department: String? = null,
    val email: String? = null
)